package com.healthree.healthree_back.order.model.dto.projection;

public interface OrderItemPorjection {
    Long getId();

    String getImageUrl();

    String getProductName();

    int getPrice();

    int getQuantity();
}
