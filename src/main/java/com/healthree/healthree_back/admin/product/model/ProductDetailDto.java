package com.healthree.healthree_back.admin.product.model;

import java.util.List;

import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
    private Long id;
    private String title;
    private String subTitle;
    private String thumbnail;
    private String topImage;
    private List<String> detailImages;
    private Integer price;
    private Integer discountPrice;
    private Integer stock;
    private Boolean isShow;

    public ProductDetailDto(ShoppingItemEntity shoppingItemEntity) {
        this.id = shoppingItemEntity.getId();
        this.title = shoppingItemEntity.getTitle();
        this.subTitle = shoppingItemEntity.getSubTitle();
        this.thumbnail = shoppingItemEntity.getThumbnail();
        this.topImage = shoppingItemEntity.getTopImage();
        this.detailImages = shoppingItemEntity.getDetailImages();
        this.price = shoppingItemEntity.getPrice();
        this.discountPrice = shoppingItemEntity.getDiscountPrice();
        this.stock = shoppingItemEntity.getStock();
        this.isShow = shoppingItemEntity.getIsShow();
    }
}
