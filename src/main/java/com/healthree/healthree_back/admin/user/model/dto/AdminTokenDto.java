package com.healthree.healthree_back.admin.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminTokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String role;
}
