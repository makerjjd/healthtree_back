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
    // private List<HealthForIssueDto> issues;
}
