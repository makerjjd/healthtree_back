package com.healthree.healthree_back.shopping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.shopping.model.dto.ShoppingListResponseDto;

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

}
