package com.healthree.healthree_back.shopping.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponseDto {
    List<ShoppingCartItemDto> shoppingCartItems;
}
