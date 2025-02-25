package com.healthree.healthree_back.shopping.model.dto;

import com.healthree.healthree_back.shopping.model.entity.ShoppingCartEntity;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartRequestDto {
    private Long itemId;
    private Integer quantity;

    public ShoppingCartEntity toEntity(UserEntity userEntity, ShoppingItemEntity shoppingItem) {
        return ShoppingCartEntity.builder()
                .userId(userEntity.getId())
                .itemId(shoppingItem.getId())
                .count(quantity)
                .build();
    }
}
