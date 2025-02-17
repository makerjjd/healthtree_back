package com.healthree.healthree_back.user.auth.naver;

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
public class NaverAccessKey {
    @JsonProperty("grant_type")
    private String grantType = "authorization_code";

    @JsonProperty("client_id")
    @Value("${spring.OAuth2.naver.client-id}")
    private String clientId;

    @JsonProperty("client_secret")
    @Value("${spring.OAuth2.naver.secret-key}")
    private String secretKey;

    @JsonProperty("code")
    private String code;

    @JsonProperty("state")
    private String state;

    @JsonProperty("redirect_uri")
    @Value("${spring.OAuth2.naver.callback-login-url}")
    private String redirectUri;
}
