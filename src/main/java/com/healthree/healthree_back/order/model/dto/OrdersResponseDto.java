package com.healthree.healthree_back.order.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersResponseDto {
    private List<OrderItemDto> orders;
    private String nextUrl;
}
