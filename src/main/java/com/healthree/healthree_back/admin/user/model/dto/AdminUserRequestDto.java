package com.healthree.healthree_back.admin.user.model.dto;

import com.healthree.healthree_back.admin.user.model.entity.AdminUserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRequestDto {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String role;
    private Boolean isDeleted;

    public AdminUserEntity toEntity() {
        return AdminUserEntity.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .role(role)
                .isDeleted(isDeleted)
                .build();
    }
}
