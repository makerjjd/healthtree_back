package com.healthree.healthree_back.user.auth.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Component
@RequiredArgsConstructor
public class KakaoAccessKey {
    @JsonProperty("grant_type")
    private String grantType = "authorization_code";

    @JsonProperty("client_id")
    @Value("${spring.OAuth2.kakao.client-id}")
    private String clientId;

    @JsonProperty("redirect_uri")
    @Value("${spring.OAuth2.kakao.callback-login-url}")
    private String redirectUri;

    @JsonProperty("client_secret")
    @Value("${spring.OAuth2.kakao.secret-key}")
    private String secretKey;

    @JsonProperty("code")
    private String code;

    @Value("${spring.OAuth2.kakao.callback-logout-url}")
    private String logoutRedirectUri;
}
