package com.healthree.healthree_back.admin.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.admin.product.model.CreateProductDetailDto;
import com.healthree.healthree_back.admin.product.model.GetProductResponseDto;
import com.healthree.healthree_back.admin.product.model.ProductDetailDto;
import com.healthree.healthree_back.admin.product.model.ProductDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.shopping.ShoppingItemRepository;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminProductService {
    private final ShoppingItemRepository shoppingItemRepository;

    @Transactional(readOnly = true)
    public GetProductResponseDto getProducts(PageRequestDto pageRequestDto) {
        Page<ShoppingItemEntity> shoppingItems = shoppingItemRepository.findAll(pageRequestDto.getPageable());

        List<ProductDto> productDtos = shoppingItems.getContent().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());

        return new GetProductResponseDto(productDtos, shoppingItems.getTotalPages(), shoppingItems.getTotalElements());
    }

    @Transactional(readOnly = true)
    public ProductDetailDto getProduct(Long id) {
        ShoppingItemEntity shoppingItemEntity = shoppingItemRepository.findById(id)
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "상품을 찾을 수 없습니다."));

        return new ProductDetailDto(shoppingItemEntity);
    }

    @Transactional(readOnly = false)
    public void createProduct(CreateProductDetailDto createProductDetailDto) {
        ShoppingItemEntity shoppingItemEntity = createProductDetailDto.toEntity();
        shoppingItemRepository.save(shoppingItemEntity);
    }

    @Transactional(readOnly = false)
    public void updateProduct(Long id, CreateProductDetailDto createProductDetailDto) {
        ShoppingItemEntity shoppingItemEntity = shoppingItemRepository.findById(id)
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "상품을 찾을 수 없습니다."));

        shoppingItemEntity.setTitle(createProductDetailDto.getTitle());
        shoppingItemEntity.setSubTitle(createProductDetailDto.getSubTitle());
        shoppingItemEntity.setThumbnail(createProductDetailDto.getThumbnail());
        shoppingItemEntity.setTopImage(createProductDetailDto.getTopImage());
        shoppingItemEntity.setDetailImages(createProductDetailDto.getDetailImages());
        shoppingItemEntity.setPrice(createProductDetailDto.getPrice());
        shoppingItemEntity.setDiscountPrice(createProductDetailDto.getDiscountPrice());
        shoppingItemEntity.setStock(createProductDetailDto.getStock());
        shoppingItemEntity.setIsShow(createProductDetailDto.getIsShow());

        shoppingItemRepository.save(shoppingItemEntity);
    }

    @Transactional(readOnly = false)
    public void deleteProduct(Long id) {
        ShoppingItemEntity shoppingItemEntity = shoppingItemRepository.findById(id)
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "상품을 찾을 수 없습니다."));

        shoppingItemRepository.delete(shoppingItemEntity);
    }
}
