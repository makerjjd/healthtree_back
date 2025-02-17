package com.healthree.healthree_back.my.dto;

import java.util.List;

import com.healthree.healthree_back.reservation.model.dto.ReservationDto;
import com.healthree.healthree_back.shopping.model.dto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyHomeResponseDto {
    private List<ReservationDto> upcomimgReservation;
    private List<ReservationDto> recentReservations;
    private List<OrderDto> recentOrders;
}
