package com.healthree.healthree_back.shopping.model.dto.projection;

public interface ShoppingCartItemProjection {
    Long getId();

    String getShoppingItemId();

    String getTitle();

    String getSubTitle();

    String getImageUrl();

    int getPrice();

    int getQuantity();

    int getStock();
}
