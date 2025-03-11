package com.healthree.healthree_back.admin.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.admin.product.model.CreateProductDetailDto;
import com.healthree.healthree_back.admin.product.model.GetProductResponseDto;
import com.healthree.healthree_back.admin.product.model.ProductDetailDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "어드민 상품 도메인", description = "어드민 상품 도메인")
@Slf4j
@RestController
@RequestMapping("/admin/products")
@AllArgsConstructor
public class AdminProductController {
    private final AdminProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getProducts(PageRequestDto pageRequestDto) {
        GetProductResponseDto getProductResponseDto = productService.getProducts(pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", getProductResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        ProductDetailDto productDetailDto = productService.getProduct(id);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", productDetailDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDetailDto createProductDetailDto) {
        productService.createProduct(createProductDetailDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
            @RequestBody CreateProductDetailDto createProductDetailDto) {
        productService.updateProduct(id, createProductDetailDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
