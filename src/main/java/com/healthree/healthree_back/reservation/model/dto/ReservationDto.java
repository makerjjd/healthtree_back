package com.healthree.healthree_back.reservation.model.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ReservationDto {
    private String hospitalName;
    private String docotrName;
    private LocalDateTime reservationDateTime;

    public ReservationDto(String hospitalName, String docotrName, LocalDateTime reservationDateTime) {
        this.hospitalName = hospitalName;
        this.docotrName = docotrName;
        this.reservationDateTime = reservationDateTime;
    }
}
