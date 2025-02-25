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
import com.healthree.healthree_back.shopping.model.dto.ShoppingCartRequestDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingCartResponseDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDetailDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingListResponseDto;
import com.healthree.healthree_back.shopping.model.dto.projection.ShoppingCartItemProjection;
import com.healthree.healthree_back.shopping.model.entity.ShoppingCartEntity;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShoppingService {
    private final ShoppingItemRepository shoppingItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;

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
            String nextUrl = "/app/shopping" + pageRequestDto.nextUrl();
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
        Slice<ShoppingCartItemProjection> shoppingCartItemProjections = shoppingCartRepository
                .findAllShoppintCartProjectionByUserId(userEntity.getId(), pageRequestDto.getPageable());

        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();

        List<ShoppingCartItemDto> shoppingItemList = new ArrayList<>();
        shoppingCartItemProjections.getContent().forEach(shoppingCartItemProjection -> {
            shoppingItemList.add(new ShoppingCartItemDto(shoppingCartItemProjection));
        });

        shoppingCartResponseDto.setShoppingCartItems(shoppingItemList);

        if (shoppingCartItemProjections.hasNext()) {
            String nextUrl = "/app/shopping/cart" + pageRequestDto.nextUrl();
            shoppingCartResponseDto.setNextUrl(nextUrl);
        }

        return shoppingCartResponseDto;
    }

    @Transactional(readOnly = false)
    public void addShoppingCart(UserEntity userEntity, ShoppingCartRequestDto shoppingCartRequestDto) {
        ShoppingItemEntity shoppingItem = shoppingItemRepository.findById(shoppingCartRequestDto.getItemId())
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 상품이 존재하지 않습니다."));

        shoppingCartRepository.save(shoppingCartRequestDto.toEntity(userEntity, shoppingItem));
    }

    @Transactional(readOnly = false)
    public void deleteShoppingCart(UserEntity userEntity, Long id) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findByIdAndUserId(id, userEntity.getId())
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 상품이 존재하지 않습니다."));

        shoppingCartRepository.delete(shoppingCartEntity);
    }

}
