package com.healthree.healthree_back.admin.hospital.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetHospitalResponseDto {
    private List<HospitalDto> hospitals;
    private Long total;
    private Integer totalPage;
}
