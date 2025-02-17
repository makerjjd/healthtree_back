package com.healthree.healthree_back.user.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthree.healthree_back.user.model.entity.UserEntity;
import com.healthree.healthree_back.user.model.type.GenderType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRes {
    @Schema(description = "ID", defaultValue = "1")
    private Long id;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "전화번호")
    private String phoneNumber;

    @Schema(description = "생년월일", defaultValue = "19860901")
    private LocalDate birth;

    @Schema(description = "성별", defaultValue = "MALE")
    private GenderType gender;

    public GetUserRes(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.birth = user.getBirth();
        this.gender = user.getGender();
    }
}
