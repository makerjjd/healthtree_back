package com.healthree.healthree_back.admin.healthReport.model.dto;

import java.util.List;

import com.healthree.healthree_back.user.model.type.GenderType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthReportDetailDto {
    private Long id;
    private Long userId;
    private String userName;
    private String userPhoneNumber;
    private String userBirth;
    private GenderType userGender;
    private String doctorNote;
    private List<HealthForIssueDto> issues;

    public HealthReportDetailDto(HealthReportDetailProjection healthReportDetailProjection,
            List<HealthForIssueDto> issues) {
        this.id = healthReportDetailProjection.getId();
        this.userId = healthReportDetailProjection.getUserId();
        this.userName = healthReportDetailProjection.getUserName();
        this.userPhoneNumber = healthReportDetailProjection.getUserPhoneNumber();
        this.userBirth = healthReportDetailProjection.getUserBirth();
        this.userGender = GenderType.valueOf(healthReportDetailProjection.getUserGender());
        this.doctorNote = healthReportDetailProjection.getDoctorNote();
        this.issues = issues;
    }
}
