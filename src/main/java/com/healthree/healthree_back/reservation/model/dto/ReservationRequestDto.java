package com.healthree.healthree_back.reservation.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.healthree.healthree_back.reservation.model.entity.ReservationEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    private Long hospitalId;
    private Long doctorId;
    private List<LocalDateTime> reservationDateTimes;

    public ReservationEntity toEntity(Long userId) {
        return ReservationEntity.builder()
                .userId(userId)
                .hospitalId(hospitalId)
                .doctorId(doctorId)
                .reservationDateTimes(reservationDateTimes)
                .isDeleted(Boolean.FALSE)
                .build();
    }
}
