package com.healthree.healthree_back.user.auth.google;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Component
@RequiredArgsConstructor
public class GoogleAccessKey {
    @JsonProperty("client_id")
    @Value("${spring.OAuth2.google.client-id}")
    private String clientId;

    @JsonProperty("redirect_uri")
    @Value("${spring.OAuth2.google.callback-login-url}")
    private String redirectUri;

    @JsonProperty("client_secret")
    @Value("${spring.OAuth2.google.client-secret}")
    private String clientSecret;

    @JsonProperty("grant_type")
    private String grantType = "authorization_code";

    @JsonProperty("code")
    private String code;
}
