package com.healthree.healthree_back.order.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.healthree.healthree_back.order.model.entity.UserOrderEntity;
import com.healthree.healthree_back.order.model.type.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private String orderNumber;
    private LocalDateTime orderDateTime;
    private int totalPrice;
    private OrderStatus status;
    private String deliveryId;
    private OrderShoppingItemDto shoppingItem;

    public OrderItemDto(UserOrderEntity userOrderEntity, OrderShoppingItemDto shoppingItem) {
        this.id = userOrderEntity.getId();
        this.orderDateTime = userOrderEntity.getOrderDateTime();
        this.orderNumber = userOrderEntity.getOrderDateTime().toString() + userOrderEntity.getId();
        this.totalPrice = userOrderEntity.getTotalPrice();
        this.status = userOrderEntity.getStatus();
        this.deliveryId = userOrderEntity.getDeliveryId();
        this.shoppingItem = shoppingItem;
    }
}
