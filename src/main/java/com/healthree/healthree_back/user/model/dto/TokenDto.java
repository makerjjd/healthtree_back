package com.healthree.healthree_back.user.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
