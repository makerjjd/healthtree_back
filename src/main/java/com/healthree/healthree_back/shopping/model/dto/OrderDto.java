package com.healthree.healthree_back.shopping.model.dto;

import java.time.LocalDateTime;

import com.healthree.healthree_back.shopping.model.type.OrderStatus;

import lombok.Data;

@Data
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
}
