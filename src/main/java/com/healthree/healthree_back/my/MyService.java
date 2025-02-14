package com.healthree.healthree_back.my;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.my.dto.MyHomeResponseDto;
import com.healthree.healthree_back.reservation.ReservationRepository;
import com.healthree.healthree_back.shopping.OrderItemRepository;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyService {
    private final ReservationRepository reservationRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public MyHomeResponseDto home(UserEntity userEntity) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        // 현재 보다 미래에 있는 모든 예약정보를 가져온다.
        reservationRepository
                .findAllByUserIdAndReservationDateTimeAfterOrderByReservationDateTimeDesc(userEntity.getId(), now);

        // reservationDateTIme <= now 인 3개 가져온다.
        reservationRepository
                .findTop3ByUserIdAndReservationDateTimeBeforeOrderByReservationDateTimeDesc(userEntity.getId(), now);

        // 주문내역중 최근 3개 가져온다.
        orderItemRepository.findTop3ByUserIdOrderByCreatedAtDesc(userEntity.getId());
    }

}
