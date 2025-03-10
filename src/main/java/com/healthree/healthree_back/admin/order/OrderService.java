package com.healthree.healthree_back.admin.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.admin.order.model.dto.AdminOrderDetailDto;
import com.healthree.healthree_back.admin.order.model.dto.AdminOrderDto;
import com.healthree.healthree_back.admin.order.model.dto.AdminOrderItemDto;
import com.healthree.healthree_back.admin.order.model.dto.AdminOrderItemProjection;
import com.healthree.healthree_back.admin.order.model.dto.GetOrdersResposneDto;
import com.healthree.healthree_back.admin.order.model.dto.UpdateOrderRequestDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.order.OrderItemRepository;
import com.healthree.healthree_back.order.UserOrderRepository;
import com.healthree.healthree_back.order.model.entity.UserOrderEntity;
import com.healthree.healthree_back.order.model.type.OrderStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final UserOrderRepository userOrderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public GetOrdersResposneDto getOrders(PageRequestDto pageRequestDto) {
        Page<UserOrderEntity> userOrders = userOrderRepository.findAll(pageRequestDto.getPageable());

        List<AdminOrderDto> adminOrders = userOrders.stream()
                .map(AdminOrderDto::new)
                .collect(Collectors.toList());

        return new GetOrdersResposneDto(adminOrders, userOrders.getTotalPages());
    }

    @Transactional(readOnly = true)
    public AdminOrderDetailDto getOrder(Long orderId) {
        UserOrderEntity userOrderEntity = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        List<AdminOrderItemProjection> adminOrderItemProjections = orderItemRepository.findAllInfoByOrderId(orderId);

        List<AdminOrderItemDto> adminOrderItemDtos = adminOrderItemProjections.stream()
                .map(AdminOrderItemDto::new)
                .collect(Collectors.toList());

        return new AdminOrderDetailDto(userOrderEntity, adminOrderItemDtos);
    }

    @Transactional(readOnly = false)
    public void updateOrder(Long id, UpdateOrderRequestDto updateOrderRequestDto) {
        UserOrderEntity userOrderEntity = userOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        userOrderEntity.setStatus(OrderStatus.valueOf(updateOrderRequestDto.getStatusCode()));
    }
}
