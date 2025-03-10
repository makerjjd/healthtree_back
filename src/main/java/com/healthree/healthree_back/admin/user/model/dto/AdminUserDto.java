package com.healthree.healthree_back.admin.user.model.dto;

import com.healthree.healthree_back.admin.user.model.entity.AdminUserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
    private String status;

    public AdminUserDto(AdminUserEntity adminUserEntity) {
        this.id = adminUserEntity.getId();
        this.email = adminUserEntity.getEmail();
        this.name = adminUserEntity.getName();
        this.phoneNumber = adminUserEntity.getPhoneNumber();
        this.role = adminUserEntity.getRole();
        this.status = adminUserEntity.getStatus();
    }
}
