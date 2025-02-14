package com.healthree.healthree_back.my.dto;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.healthree.healthree_back.reservation.model.dto.ReservationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyHomeResponseDto {
    private List<ReservationDto> upcomimgReservation;
    private List<ReservationDto> recentReservations;
    private List<OrderDto> recentOrders;
}
