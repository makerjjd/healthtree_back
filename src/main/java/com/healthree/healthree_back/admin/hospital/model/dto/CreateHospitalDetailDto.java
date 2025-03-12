package com.healthree.healthree_back.admin.hospital.model.dto;

import java.util.List;

import com.healthree.healthree_back.hosiptal.model.dto.DoctorDto;
import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHospitalDetailDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String description;
    private List<DoctorDto> doctors;

    public HospitalEntity toEntity() {
        return HospitalEntity.builder()
                .name(name)
                .address(address)
                .phone(phoneNumber)
                .description(description)
                .build();
    }
}
