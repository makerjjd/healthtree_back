package com.healthree.healthree_back.reservation.model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private String doctorName;
    private List<LocalDateTime> reservationRequestDateTimes;
    private LocalDateTime reservationDateTime;

    public ReservationDto(Long id, String hospitalName, String doctorName,
            List<LocalDateTime> reservationRequestDateTimes, LocalDateTime reservationDateTime) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.doctorName = doctorName;
        this.reservationDateTime = reservationDateTime;
    }

    public static ReservationDto toReservationDto(
            HospitalReservationSummaryProjection hospitalReservationSummaryProjection) {

        List<LocalDateTime> reservationRequestDateTimeList = Arrays
                .stream(hospitalReservationSummaryProjection.getReservationRequestDateTimes().split(","))
                .map(dateTime -> LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .collect(Collectors.toList());

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(hospitalReservationSummaryProjection.getId());
        reservationDto.setHospitalName(hospitalReservationSummaryProjection.getHospitalName());
        reservationDto.setDoctorName(hospitalReservationSummaryProjection.getDoctorName());
        reservationDto.setReservationRequestDateTimes(reservationRequestDateTimeList);
        reservationDto.setReservationDateTime(hospitalReservationSummaryProjection.getReservationDateTime());

        return reservationDto;
    }
}
