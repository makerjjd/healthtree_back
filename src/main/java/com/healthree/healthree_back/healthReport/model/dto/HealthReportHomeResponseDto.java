package com.healthree.healthree_back.healthReport.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthReportHomeResponseDto {
    private String doctorNote;
    private List<HealthProductForIssueDto> healthProductForIssueDtos;
    private List<HospitalForIssueDto> hospitalForIssueDtos;
}
