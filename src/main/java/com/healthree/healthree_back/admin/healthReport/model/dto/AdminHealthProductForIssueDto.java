package com.healthree.healthree_back.admin.healthReport.model.dto;

import java.util.List;

import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminHealthProductForIssueDto {
    private String description;
    private List<ShoppingItemDto> productItemDtos;
}
