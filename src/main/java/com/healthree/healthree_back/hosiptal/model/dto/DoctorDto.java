package com.healthree.healthree_back.hosiptal.model.dto;

import com.healthree.healthree_back.hosiptal.model.entity.DoctorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String image;
    private String description;

    public DoctorDto(DoctorEntity doctorEntity) {
        this.id = doctorEntity.getId();
        this.name = doctorEntity.getName();
        this.image = doctorEntity.getImage();
        this.description = doctorEntity.getDescription();
    }

    public DoctorEntity toEntity(Long hospitalId) {
        return DoctorEntity.builder()
                .id(id)
                .hospitalId(hospitalId)
                .name(name)
                .image(image)
                .description(description)
                .build();
    }
}
