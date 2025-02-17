package com.healthree.healthree_back.shopping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.shopping.model.dto.ShoppingCartItemDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingCartResponseDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDetailDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingListResponseDto;
import com.healthree.healthree_back.shopping.model.dto.projection.ShoppingCartItemProjection;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

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

    @Transactional(readOnly = true)
    public ShoppingItemDetailDto getShoppingItemDetail(Long id) {
        ShoppingItemEntity shoppingItem = shoppingItemRepository.findById(id)
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 상품이 존재하지 않습니다."));

        return new ShoppingItemDetailDto(shoppingItem);
    }

    @Transactional(readOnly = true)
    public ShoppingCartResponseDto getShoppingCart(UserEntity userEntity, PageRequestDto pageRequestDto) {
        List<ShoppingCartItemProjection> shoppingCartItemProjections = shoppingItemRepository
                .findAllShoppintCartProjectionByUserId(userEntity.getId(), pageRequestDto.getPageable());

        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();

        List<ShoppingCartItemDto> shoppingItemList = new ArrayList<>();
        shoppingCartItemProjections.forEach(shoppingCartItemProjection -> {
            shoppingItemList.add(new ShoppingCartItemDto(shoppingCartItemProjection));
        });

        shoppingCartResponseDto.setShoppingCartItems(shoppingItemList);

        return shoppingCartResponseDto;
    }

}
