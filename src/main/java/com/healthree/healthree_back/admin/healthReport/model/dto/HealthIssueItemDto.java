package com.healthree.healthree_back.admin.healthReport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthIssueItemDto {
    private String issueName;
    private HealthIssueProductDto products;
    private HealthIssueHospitalDto hospitals;
}
