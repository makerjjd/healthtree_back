package com.healthree.healthree_back.order.model.dto;

import com.healthree.healthree_back.order.model.dto.projection.OrderItemPorjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderShoppingItemDto {
    private Long id;
    private String imageUrl;
    private String productName;
    private int price;
    private int quantity;

    public OrderShoppingItemDto(OrderItemPorjection orderItemPorjection) {
        this.id = orderItemPorjection.getId();
        this.imageUrl = orderItemPorjection.getImageUrl();
        this.productName = orderItemPorjection.getItemName();
        this.price = orderItemPorjection.getPrice();
        this.quantity = orderItemPorjection.getQuantity();
    }
}
