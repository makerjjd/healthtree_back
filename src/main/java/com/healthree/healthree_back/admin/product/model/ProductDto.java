package com.healthree.healthree_back.admin.product.model;

import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private Boolean isShow;

    public ProductDto(ShoppingItemEntity shoppingItemEntity) {
        this.id = shoppingItemEntity.getId();
        this.name = shoppingItemEntity.getTitle();
        this.imageUrl = shoppingItemEntity.getThumbnail();
        this.price = shoppingItemEntity.getPrice();
        this.stock = shoppingItemEntity.getStock();
        this.isShow = shoppingItemEntity.getIsShow();
    }
}
