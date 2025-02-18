package com.healthree.healthree_back.reservation.model.dto;

import java.time.LocalDateTime;

import com.healthree.healthree_back.my.dto.projection.HospitalReservationSummaryProjection;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ReservationDto {
    private Long id;
    private String hospitalName;
    private String docotrName;
    private LocalDateTime reservationDateTime;

    public ReservationDto(Long id, String hospitalName, String docotrName, LocalDateTime reservationDateTime) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.docotrName = docotrName;
        this.reservationDateTime = reservationDateTime;
    }

    public static ReservationDto toReservationDto(
            HospitalReservationSummaryProjection hospitalReservationSummaryProjection) {
        return ReservationDto.builder()
                .id(hospitalReservationSummaryProjection.getId())
                .hospitalName(hospitalReservationSummaryProjection.getHospitalName())
                .docotrName(hospitalReservationSummaryProjection.getDoctorName())
                .reservationDateTime(hospitalReservationSummaryProjection.getReservationDateTime())
                .build();
    }
}
