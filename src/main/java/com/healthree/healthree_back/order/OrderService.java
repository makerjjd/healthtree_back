package com.healthree.healthree_back.order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.order.model.dto.OrderItemDto;
import com.healthree.healthree_back.order.model.dto.OrderRequestDto;
import com.healthree.healthree_back.order.model.dto.OrderShoppingItemDto;
import com.healthree.healthree_back.order.model.dto.OrderShoppingItemRequestDto;
import com.healthree.healthree_back.order.model.dto.OrdersResponseDto;
import com.healthree.healthree_back.order.model.dto.projection.OrderItemPorjection;
import com.healthree.healthree_back.order.model.entity.OrderItemEntity;
import com.healthree.healthree_back.order.model.entity.UserOrderEntity;
import com.healthree.healthree_back.order.model.type.OrderStatus;
import com.healthree.healthree_back.shopping.ShoppingItemRepository;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final UserOrderRepository userOrderRepository;
    private final ShoppingItemRepository shoppingItemRepository;

    @Transactional(readOnly = true)
    public OrdersResponseDto getOrderList(UserEntity userEntity, PageRequestDto pageRequestDto) {
        Slice<UserOrderEntity> userOrderEntities = userOrderRepository
                .findAllByUserIdOrderByOrderDateTimeDesc(userEntity.getId(), pageRequestDto.getPageable());

        List<Long> orderIds = userOrderEntities.stream().map(UserOrderEntity::getId).toList();

        List<OrderItemPorjection> orderItemPorjections = orderItemRepository.findOrderItemInfosByOrderIds(orderIds);
        Map<Long, List<OrderItemPorjection>> orderItemMap = orderItemPorjections.stream()
                .collect(Collectors.groupingBy(OrderItemPorjection::getId));

        List<OrderItemDto> orderItemDtos = userOrderEntities.stream().map(userOrderEntity -> {
            List<OrderShoppingItemDto> orderShoppintItemDtos = orderItemMap.get(userOrderEntity.getId()).stream()
                    .map(orderItemPorjection -> {
                        return new OrderShoppingItemDto(orderItemPorjection);
                    }).toList();

            return new OrderItemDto(userOrderEntity, orderShoppintItemDtos);
        }).toList();

        String nextUrl = null;
        if (userOrderEntities.hasNext()) {
            nextUrl = "/app/orders?page=" + (pageRequestDto.getPage() + 1);
        }

        return new OrdersResponseDto(orderItemDtos, nextUrl);
    }

    @Transactional(readOnly = true)
    public OrderItemDto getOrderDetail(UserEntity userEntity, Long id) {
        UserOrderEntity userOrderEntity = userOrderRepository.findByIdAndUserId(id, userEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("주문정보가 없습니다."));

        List<OrderItemPorjection> orderItemPorjections = orderItemRepository.findOrderItemInfosByOrderIds(List.of(id));
        List<OrderShoppingItemDto> orderShoppintItemDtos = orderItemPorjections.stream().map(OrderShoppingItemDto::new)
                .toList();

        return new OrderItemDto(userOrderEntity, orderShoppintItemDtos);
    }

    @Transactional(readOnly = false)
    public void cancelOrder(UserEntity userEntity, Long id) {
        UserOrderEntity userOrderEntity = userOrderRepository.findByIdAndUserId(id, userEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("주문정보가 없습니다."));

        userOrderEntity.setStatus(OrderStatus.CANCEL_REQUESTED);
        userOrderRepository.save(userOrderEntity);

        // 어드민에서 해당 항목을 shopping item 에 수량 추가해주는 로직이 필요함.
        // 어드민에서 결제 취소 처리를 해야함.
        // 어드민에 알림 필요.
    }

    @Transactional(readOnly = false)
    public void order(UserEntity userEntity, OrderRequestDto orderRequestDto) {
        // shopping item에 수량을 비교해서 빼주는 로직이 필요함.
        List<Long> shoppingItemIds = orderRequestDto.getOrderItems().stream()
                .map(OrderShoppingItemRequestDto::getItemId)
                .toList();

        List<ShoppingItemEntity> shoppingItemEntities = shoppingItemRepository.findAllByIdIn(shoppingItemIds);
        Map<Long, ShoppingItemEntity> shoppingItemMap = shoppingItemEntities.stream()
                .collect(Collectors.toMap(ShoppingItemEntity::getId, shoppingItemEntity -> shoppingItemEntity));

        orderRequestDto.getOrderItems().forEach(orderItem -> {
            ShoppingItemEntity shoppingItemEntity = shoppingItemMap.get(orderItem.getItemId());
            if (shoppingItemEntity.getStock() < orderItem.getQuantity()) {
                throw new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_ENOUGH, "재고가 부족합니다.");
            } else {
                shoppingItemEntity.setStock(shoppingItemEntity.getStock() - orderItem.getQuantity());
            }
        });

        shoppingItemRepository.saveAll(shoppingItemEntities);

        // 결제 로직이 필요함.
        // 결제 로직이 필요함.

        UserOrderEntity userOrderEntity = orderRequestDto.toEntity(userEntity);
        userOrderEntity = userOrderRepository.save(userOrderEntity);

        Long orderId = userOrderEntity.getId();

        List<OrderItemEntity> orderItemEntities = orderRequestDto.getOrderItems().stream()
                .map(orderItemDto -> orderItemDto.toEntity(orderId))
                .toList();

        orderItemRepository.saveAll(orderItemEntities);

        // 어드민에 알림 필요.
        // 어드민에 알림 필요.
    }

}
