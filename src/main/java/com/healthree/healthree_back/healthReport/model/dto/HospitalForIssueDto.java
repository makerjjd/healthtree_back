package com.healthree.healthree_back.healthReport.model.dto;

import java.util.List;

import com.healthree.healthree_back.hosiptal.model.dto.HospitalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalForIssueDto {
    private String issueName;
    private List<HospitalDto> hospitalDtos;
}
