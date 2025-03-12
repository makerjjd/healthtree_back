package com.healthree.healthree_back.admin.hospital.model.dto;

import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HospitalDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String description;

    public HospitalDto(HospitalEntity hospitalEntity) {
        this.id = hospitalEntity.getId();
        this.name = hospitalEntity.getName();
        this.address = hospitalEntity.getAddress();
        this.phoneNumber = hospitalEntity.getPhone();
        this.description = hospitalEntity.getDescription();
    }
}
