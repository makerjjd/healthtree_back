package com.healthree.healthree_back.admin.healthReport.model.dto;

import java.time.LocalDate;

import com.healthree.healthree_back.healthReport.model.entity.HealthReportEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthReportDto {
    private Long id;
    private Long userId;
    private String checkUpId;
    private Long hospitalId;
    private Long doctorId;
    private String doctorNote;
    private LocalDate reportDate;

    public HealthReportDto(HealthReportEntity healthReportEntity) {
        this.id = healthReportEntity.getId();
        this.userId = healthReportEntity.getUserId();
        this.checkUpId = healthReportEntity.getCheckUpId();
        this.hospitalId = healthReportEntity.getHospitalId();
        this.doctorId = healthReportEntity.getDoctorId();
        this.doctorNote = healthReportEntity.getDoctorNote();
        this.reportDate = healthReportEntity.getReportDate();
    }
}
