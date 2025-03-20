package com.healthree.healthree_back.admin.healthReport.model.dto;

import java.util.List;

import com.healthree.healthree_back.admin.product.model.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResponseForReport {
    List<ProductDto> products;
    String nextUrl;
}
