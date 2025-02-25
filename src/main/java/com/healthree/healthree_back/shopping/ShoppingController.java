package com.healthree.healthree_back.shopping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.common.utils.AuthUtil;
import com.healthree.healthree_back.shopping.model.dto.ShoppingCartRequestDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingCartResponseDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDetailDto;
import com.healthree.healthree_back.shopping.model.dto.ShoppingListResponseDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "쇼핑정보 도메인", description = "쇼핑정보 도메인")
@Slf4j
@RestController
@RequestMapping("/app/shopping")
@AllArgsConstructor
public class ShoppingController {
    private final ShoppingService shoppingService;

    @GetMapping("")
    @Parameter(name = "pageRequestDto", hidden = true)
    @Parameter(name = "page", description = "페이지 값", required = false)
    @Parameter(name = "search", description = "검색어", required = false)
    public ResponseEntity<?> home(PageRequestDto pageRequestDto) {
        ShoppingListResponseDto shoppingListResponseDto = shoppingService.getShoppingList(pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", shoppingListResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        ShoppingItemDetailDto shoppingItemDetailDto = shoppingService.getShoppingItemDetail(id);

        return new ResponseEntity<ApiResponseMessage>(
                ApiResponseMessage.successWithData("", shoppingItemDetailDto), HttpStatus.OK);
    }

    @GetMapping("/cart")
    @Parameter(name = "pageRequestDto", hidden = true)
    @Parameter(name = "page", description = "페이지 값", required = false)
    @Parameter(name = "search", description = "검색어", required = false)
    public ResponseEntity<?> cart(Authentication authentication, PageRequestDto pageRequestDto) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        ShoppingCartResponseDto shoppingCartResponseDto = shoppingService.getShoppingCart(userEntity, pageRequestDto);
        return new ResponseEntity<ApiResponseMessage>(ApiResponseMessage.successWithData("", shoppingCartResponseDto),
                HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addCart(Authentication authentication,
            @RequestBody ShoppingCartRequestDto shoppingCartRequestDto) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        shoppingService.addShoppingCart(userEntity, shoppingCartRequestDto);
        return new ResponseEntity<ApiResponseMessage>(ApiResponseMessage.successWithData("", ""), HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<?> deleteCart(Authentication authentication, @PathVariable Long id) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        shoppingService.deleteShoppingCart(userEntity, id);
        return new ResponseEntity<ApiResponseMessage>(ApiResponseMessage.successWithData("", ""), HttpStatus.OK);
    }

}
