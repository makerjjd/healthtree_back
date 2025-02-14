package com.healthree.healthree_back.hosiptal.model.dto;

import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto {
    private Long id;
    private String name;

    public HospitalDto(HospitalEntity hospitalEntity) {
        this.id = hospitalEntity.getId();
        this.name = hospitalEntity.getName();
    }
}
