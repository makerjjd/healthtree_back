package com.healthree.healthree_back.user.model.dto;

import com.healthree.healthree_back.user.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTempRequestDto {
    @Schema(description = "검진 아이디")
    private String checkUpId;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "전화번호")
    private String phoneNumber;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .checkUpId(checkUpId)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
