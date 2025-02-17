package com.healthree.healthree_back.my.dto.projection;

public interface OrderItemSummaryProjection {
    String getItemName();

    int getQuantity();

    String getCreatedAt();

    Integer getOrderStatus();
}
