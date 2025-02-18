package com.healthree.healthree_back.order.model.dto;

import java.time.LocalDateTime;

import com.healthree.healthree_back.my.dto.projection.OrderItemSummaryProjection;
import com.healthree.healthree_back.order.model.type.OrderStatus;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class OrderDto {
    public String itemName;
    public int quantity;
    public LocalDateTime orderDateTime;
    public OrderStatus orderStatus;

    public OrderDto(String itemName, int quantity, LocalDateTime orderDateTime, OrderStatus orderStatus) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.orderDateTime = orderDateTime;
        this.orderStatus = orderStatus;
    }

    public static OrderDto toOrderDto(OrderItemSummaryProjection orderItemSummaryProjection) {
        return OrderDto.builder()
                .itemName(orderItemSummaryProjection.getItemName())
                .quantity(orderItemSummaryProjection.getQuantity())
                .orderDateTime(LocalDateTime.parse(orderItemSummaryProjection.getCreatedAt()))
                .orderStatus(OrderStatus.valueOf(orderItemSummaryProjection.getOrderStatus()))
                .build();
    }
}
