package com.healthree.healthree_back.admin.user.model.dto;

import java.time.LocalDate;

import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDetailResponseDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private LocalDate birth;
    private String gender;
    private boolean isDeleted;

    public GetUserDetailResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.birth = userEntity.getBirth();
        this.gender = userEntity.getGender().name();
        this.isDeleted = userEntity.isDeleted();
    }
}
