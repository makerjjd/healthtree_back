package com.healthree.healthree_back.shopping.model.entity;

import java.util.List;

import com.healthree.healthree_back.common.model.converter.LocalDateTimeListConverter;
import com.healthree.healthree_back.common.model.converter.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_item")
@Builder
public class ShoppingItemEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String subTitle;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String topImage;

    @Column(nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> detailImages;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = true)
    private Integer discountPrice;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Boolean isShow;
}
