package com.healthree.healthree_back.admin.order.model.dto;

public interface AdminOrderItemProjection {
    Long getId();

    Long getItemId();

    String getItemName();

    Integer getCount();

    Integer getPrice();
}
