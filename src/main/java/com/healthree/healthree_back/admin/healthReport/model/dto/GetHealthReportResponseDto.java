package com.healthree.healthree_back.admin.healthReport.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetHealthReportResponseDto {
    private List<HealthReportDto> healthReportDtos;
    private Long total;
    private Integer totalPages;
}
