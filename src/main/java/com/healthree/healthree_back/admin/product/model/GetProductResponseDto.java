package com.healthree.healthree_back.admin.product.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResponseDto {
    private List<ProductDto> products;
    private Integer total;
}
