package com.healthree.healthree_back.admin.healthReport.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthIssueProductDto {
    private String description;
    private List<Long> productIds;
}
