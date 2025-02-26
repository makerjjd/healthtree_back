package com.healthree.healthree_back.my;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.my.dto.MyHomeResponseDto;
import com.healthree.healthree_back.my.dto.projection.HospitalReservationSummaryProjection;
import com.healthree.healthree_back.notification.NotificationRepository;
import com.healthree.healthree_back.notification.model.dto.NotificationCartCountResponseDto;
import com.healthree.healthree_back.order.OrderItemRepository;
import com.healthree.healthree_back.order.UserOrderRepository;
import com.healthree.healthree_back.order.model.dto.OrderItemDto;
import com.healthree.healthree_back.order.model.dto.OrderShoppingItemDto;
import com.healthree.healthree_back.order.model.dto.projection.OrderItemPorjection;
import com.healthree.healthree_back.order.model.entity.UserOrderEntity;
import com.healthree.healthree_back.reservation.ReservationRepository;
import com.healthree.healthree_back.reservation.model.dto.ReservationDto;
import com.healthree.healthree_back.shopping.ShoppingCartRepository;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyService {
        private final ReservationRepository reservationRepository;
        private final UserOrderRepository userOrderRepository;
        private final OrderItemRepository orderItemRepository;
        private final NotificationRepository notificationRepository;
        private final ShoppingCartRepository shoppingCartRepository;

        @Transactional(readOnly = true)
        public MyHomeResponseDto home(UserEntity userEntity) {
                LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

                // 현재 보다 미래에 있는 모든 예약정보를 가져온다.
                List<HospitalReservationSummaryProjection> upCommingReservationSummaryProjections = reservationRepository
                                .findAllByUserIdAndReservationDateTimeAfterOrderByReservationDateTimeDesc(
                                                userEntity.getId(), now);

                // reservationDateTIme <= now 인 3개 가져온다.
                List<HospitalReservationSummaryProjection> reservedSummaryProjections = reservationRepository
                                .findTop3ByUserIdAndReservationDateTimeBeforeOrderByReservationDateTimeDesc(
                                                userEntity.getId(),
                                                now);

                // 주문내역중 최근 3개 가져온다.
                List<UserOrderEntity> userOrderEntities = userOrderRepository
                                .findTop3ByUserIdOrderByOrderDateTimeDesc(userEntity.getId());

                List<Long> orderIds = userOrderEntities.stream().map(UserOrderEntity::getId).toList();

                List<OrderItemPorjection> orderItemSummaryProjections = orderItemRepository
                                .findOrderItemInfosByOrderIds(orderIds);
                Map<Long, List<OrderItemPorjection>> orderItemMap = orderItemSummaryProjections.stream()
                                .collect(Collectors.groupingBy(OrderItemPorjection::getOrderId));

                // dto maaping
                List<ReservationDto> upcomimgReservation = upCommingReservationSummaryProjections.stream()
                                .map(ReservationDto::toReservationDto).toList();

                List<ReservationDto> recentReservations = reservedSummaryProjections.stream()
                                .map(ReservationDto::toReservationDto)
                                .toList();

                // private Long id;
                // private String imageUrl;
                // private String productName;
                // private int price;
                // private int quantity;

                List<OrderItemDto> orderItemDtos = userOrderEntities.stream().map(userOrderEntity -> {
                        List<OrderItemPorjection> orderItemPorjections = orderItemMap.get(userOrderEntity.getId());
                        String imageUrl = "";
                        String productName = "";
                        int price = 0;
                        int quantity = 0;

                        if (orderItemPorjections != null && orderItemPorjections.size() > 0) {
                                imageUrl = orderItemPorjections.get(0).getImageUrl();
                                productName = orderItemPorjections.get(0).getItemName();

                                if (orderItemPorjections.size() > 1) {
                                        productName += " 외 " + (orderItemPorjections.size() - 1) + "개";
                                }
                        }

                        for (OrderItemPorjection orderItemPorjection : orderItemPorjections) {
                                price += orderItemPorjection.getPrice();
                                quantity += orderItemPorjection.getQuantity();
                        }

                        OrderShoppingItemDto orderShoppintItemDtos = new OrderShoppingItemDto(imageUrl, productName,
                                        price, quantity);

                        return new OrderItemDto(userOrderEntity, orderShoppintItemDtos);
                }).toList();

                return MyHomeResponseDto.builder().upcomimgReservation(upcomimgReservation)
                                .recentReservations(recentReservations).recentOrders(orderItemDtos).build();

        }

        @Transactional(readOnly = true)
        public NotificationCartCountResponseDto getNotifiationCartCoun(UserEntity userEntity) {
                Long notificationCount = notificationRepository.countByUserIdAndIsRead(userEntity.getId(),
                                Boolean.FALSE);

                Long shoppingCartCount = shoppingCartRepository.countByUserId(userEntity.getId());

                return new NotificationCartCountResponseDto(notificationCount, shoppingCartCount);
        }

}
