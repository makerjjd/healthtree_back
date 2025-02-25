package com.healthree.healthree_back.my;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                                .collect(Collectors.groupingBy(OrderItemPorjection::getId));

                // dto maaping
                List<ReservationDto> upcomimgReservation = upCommingReservationSummaryProjections.stream()
                                .map(ReservationDto::toReservationDto).toList();

                List<ReservationDto> recentReservations = reservedSummaryProjections.stream()
                                .map(ReservationDto::toReservationDto)
                                .toList();

                List<OrderItemDto> orderItemDtos = userOrderEntities.stream().map(userOrderEntity -> {
                        List<OrderShoppingItemDto> orderShoppintItemDtos = orderItemMap.get(userOrderEntity.getId())
                                        .stream()
                                        .map(orderItemPorjection -> {
                                                return new OrderShoppingItemDto(orderItemPorjection);
                                        }).toList();

                        return new OrderItemDto(userOrderEntity, orderShoppintItemDtos);
                }).toList();

                return MyHomeResponseDto.builder().upcomimgReservation(upcomimgReservation)
                                .recentReservations(recentReservations)
                                .recentOrders(orderItemDtos).build();
        }

        @Transactional(readOnly = true)
        public NotificationCartCountResponseDto getNotifiationCartCoun(UserEntity userEntity) {
                Long notificationCount = notificationRepository.countByUserIdAndIsRead(userEntity.getId(),
                                Boolean.FALSE);

                Long shoppingCartCount = shoppingCartRepository.countByUserId(userEntity.getId());

                return new NotificationCartCountResponseDto(notificationCount, shoppingCartCount);
        }

}
