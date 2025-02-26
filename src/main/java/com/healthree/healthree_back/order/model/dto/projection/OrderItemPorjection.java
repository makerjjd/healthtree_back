package com.healthree.healthree_back.order.model.dto.projection;

public interface OrderItemPorjection {
    Long getOrderId();

    Long getId();

    String getImageUrl();

    String getItemName();

    int getPrice();

    int getQuantity();
}
