package com.healthree.healthree_back.shopping.model.dto;

import java.util.List;

import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingItemDetailDto {
    private Long id;
    private String title;
    private String subTitle;
    private String thumbnail;
    private String topImage;
    private List<String> detailImages;
    private Integer price;
    private Integer discountPrice;

    public ShoppingItemDetailDto(ShoppingItemEntity shoppingItem) {
        this.id = shoppingItem.getId();
        this.title = shoppingItem.getTitle();
        this.subTitle = shoppingItem.getSubTitle();
        this.thumbnail = shoppingItem.getThumbnail();
        this.topImage = shoppingItem.getTopImage();
        this.detailImages = shoppingItem.getDetailImages();
        this.price = shoppingItem.getPrice();
        this.discountPrice = shoppingItem.getDiscountPrice();
    }
}
