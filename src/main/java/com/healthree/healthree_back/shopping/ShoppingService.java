package com.healthree.healthree_back.shopping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingListResponseDto;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShoppingService {
    private final ShoppingItemRepository shoppingItemRepository;

    @Transactional(readOnly = true)
    public ShoppingListResponseDto getShoppingList(PageRequestDto pageRequestDto) {
        Slice<ShoppingItemEntity> shoppingItems = null;

        if (pageRequestDto.getSearch() != null) {
            shoppingItems = shoppingItemRepository.findByTitleContaining(pageRequestDto.getSearch(),
                    pageRequestDto.getPageable());
        } else {
            shoppingItems = shoppingItemRepository.findAll(pageRequestDto.getPageable());
        }

        ShoppingListResponseDto shoppingListResponseDto = new ShoppingListResponseDto();

        List<ShoppingItemDto> shoppingItemList = new ArrayList<>();
        shoppingItems.forEach(shoppingItem -> {
            shoppingItemList.add(new ShoppingItemDto(shoppingItem));
        });
        shoppingListResponseDto.setShoppingList(shoppingItemList);

        if (shoppingItems.hasNext()) {
            String nextUrl = "/app/shopping?page=" + shoppingItems.nextPageable().getPageNumber();
            shoppingListResponseDto.setNextUrl(nextUrl);
        }

        return shoppingListResponseDto;
    }

}
