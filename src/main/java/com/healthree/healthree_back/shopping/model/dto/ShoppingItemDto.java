package com.healthree.healthree_back.shopping.model.dto;

import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingItemDto {
    private Long id;
    private String title;
    private String subTitle;
    private String imageUrl;
    private Integer price;
    private Integer discountPrice;

    public ShoppingItemDto(ShoppingItemEntity shoppingItemEntity) {
        this.id = shoppingItemEntity.getId();
        this.title = shoppingItemEntity.getTitle();
        this.subTitle = shoppingItemEntity.getSubTitle();
        this.imageUrl = shoppingItemEntity.getThumbnail();
        this.price = shoppingItemEntity.getPrice();
        this.discountPrice = shoppingItemEntity.getDiscountPrice();
    }
}
