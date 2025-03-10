package com.healthree.healthree_back.admin.user.model.dto;

import java.time.LocalDateTime;

import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Boolean isDeleted;
    private LocalDateTime createdAt;

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.isDeleted = userEntity.isDeleted();
        this.createdAt = userEntity.getCreatedAt();
    }
}