package com.healthree.healthree_back.admin.healthReport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.admin.healthReport.model.dto.GetHealthReportResponseDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthReportDetailDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthReportDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.healthReport.HealthReportRepository;
import com.healthree.healthree_back.healthReport.model.entity.HealthReportEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminHealthReportService {
    private final HealthReportRepository healthReportRepository;

    @Transactional(readOnly = true)
    public GetHealthReportResponseDto getHealthReports(PageRequestDto pageRequestDto) {
        Page<HealthReportEntity> healthReportEntities = healthReportRepository
                .findAll(pageRequestDto.getPageable());

        List<HealthReportDto> healthReportDtos = new ArrayList<>();
        healthReportEntities.forEach(healthReportEntity -> {
            healthReportDtos.add(new HealthReportDto(healthReportEntity));
        });

        return new GetHealthReportResponseDto(healthReportDtos, healthReportEntities.getTotalElements(),
                healthReportEntities.getTotalPages());
    }

    @Transactional(readOnly = true)
    public HealthReportDetailDto getHealthReport(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHealthReport'");
    }
}
