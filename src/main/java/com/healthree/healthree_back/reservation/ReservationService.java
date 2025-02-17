package com.healthree.healthree_back.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.reservation.model.dto.ReservationRequestDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = false)
    public void reservation(UserEntity userEntity, ReservationRequestDto reservationRequestDto) {
        reservationRepository.save(reservationRequestDto.toEntity(userEntity.getId()));
    }
}
