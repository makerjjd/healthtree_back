package com.healthree.healthree_back.shopping.model.dto.projection;

public interface ShoppingCartItemProjection {
    Long getId();

    Long getShoppingItemId();

    String getTitle();

    String getSubTitle();

    String getImageUrl();

    int getPrice();

    int getQuantity();

    int getStock();
}
