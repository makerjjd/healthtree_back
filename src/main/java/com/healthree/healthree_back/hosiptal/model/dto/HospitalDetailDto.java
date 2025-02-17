package com.healthree.healthree_back.hosiptal.model.dto;

import java.util.List;

import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDetailDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private List<DoctorDto> doctors;

    public HospitalDetailDto(HospitalEntity hospitalEntity, List<DoctorDto> doctors) {
        this.id = hospitalEntity.getId();
        this.name = hospitalEntity.getName();
        this.address = hospitalEntity.getAddress();
        this.phone = hospitalEntity.getPhone();
        this.description = hospitalEntity.getDescription();
        this.doctors = doctors;
    }
}
