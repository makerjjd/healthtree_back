package com.healthree.healthree_back.shopping.model.dto;

import com.healthree.healthree_back.shopping.model.dto.projection.ShoppingCartItemProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemDto {
    private Long id;
    private Long shoppingItemId;
    private String title;
    private String subTitle;
    private String imageUrl;
    private int price;
    private int quantity;

    public ShoppingCartItemDto(ShoppingCartItemProjection shoppingCartItemProjection) {
        this.id = shoppingCartItemProjection.getId();
        this.shoppingItemId = shoppingCartItemProjection.getShoppingItemId();
        this.title = shoppingCartItemProjection.getTitle();
        this.subTitle = shoppingCartItemProjection.getSubTitle();
        this.imageUrl = shoppingCartItemProjection.getImageUrl();
        this.price = shoppingCartItemProjection.getPrice();
        this.quantity = shoppingCartItemProjection.getQuantity();
    }
}
