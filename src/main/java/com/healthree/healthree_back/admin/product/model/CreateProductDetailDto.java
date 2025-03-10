package com.healthree.healthree_back.admin.product.model;

import java.util.List;

import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDetailDto {
    private String title;
    private String subTitle;
    private String thumbnail;
    private String topImage;
    private List<String> detailImages;
    private Integer price;
    private Integer discountPrice;
    private Integer stock;
    private Boolean isShow;

    public ShoppingItemEntity toEntity() {
        return ShoppingItemEntity.builder()
                .title(title)
                .subTitle(subTitle)
                .thumbnail(thumbnail)
                .topImage(topImage)
                .detailImages(detailImages)
                .price(price)
                .discountPrice(discountPrice)
                .stock(stock)
                .isShow(isShow)
                .build();
    }
}
