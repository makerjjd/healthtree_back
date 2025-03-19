package com.healthree.healthree_back.admin.healthReport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthForIssueDto {
    private AdminHealthProductForIssueDto healthProductDto;
    private AdminHospitalForIssueDto hospitalDto;
    private String issueName;
}
