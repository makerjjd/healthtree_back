package com.healthree.healthree_back.user.model.dto;

import com.healthree.healthree_back.user.auth.GetSocialOAuthRes;

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
public class TokenDto implements GetSocialOAuthRes {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
