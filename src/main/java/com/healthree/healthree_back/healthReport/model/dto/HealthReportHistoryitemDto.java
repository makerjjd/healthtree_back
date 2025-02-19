package com.healthree.healthree_back.healthReport.model.dto;

import java.time.LocalDate;

import com.healthree.healthree_back.healthReport.model.entity.HealthReportEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthReportHistoryitemDto {
    private Long id;
    private LocalDate reportDate;
    private String dockerNote;

    public HealthReportHistoryitemDto(HealthReportEntity healthReportEntity) {
        this.id = healthReportEntity.getId();
        this.reportDate = healthReportEntity.getReportDate();
        this.dockerNote = healthReportEntity.getDoctorNote();
    }
}
