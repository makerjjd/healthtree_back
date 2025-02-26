package com.healthree.healthree_back.order.model.dto;

import com.healthree.healthree_back.order.model.entity.OrderItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderShoppingItemRequestDto {
    private Long itemId;
    private int quantity;
    private Integer price;
    private Long cartId;

    public OrderItemEntity toEntity(Long orderId) {
        return OrderItemEntity.builder()
                .orderId(orderId)
                .itemId(itemId)
                .count(quantity)
                .price(price)
                .build();
    }
}
