package com.healthree.healthree_back.user.model.type;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonValue;
import com.healthree.healthree_back.user.auth.google.GoogleOauth;
import com.healthree.healthree_back.user.auth.kakao.KakaoOauth;
import com.healthree.healthree_back.user.auth.naver.NaverOauth;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum SocialLoginType {
    GOOGLE,
    KAKAO,
    NAVER;

    @Getter
    private SocialOauth socialOauth;

    @JsonValue
    public String getSocialLoginName() {
        return name().toLowerCase();
    }

    @Component
    @RequiredArgsConstructor
    private static class SocialOauthInjector {
        private final GoogleOauth google;
        private final KakaoOauth kakao;
        private final NaverOauth naver;

        @PostConstruct
        public void postConstruct() {
            SocialLoginType.KAKAO.injectSocialOauth(kakao);
            SocialLoginType.NAVER.injectSocialOauth(naver);
            SocialLoginType.GOOGLE.injectSocialOauth(google);
        }
    }

    private void injectSocialOauth(SocialOauth socialOauth) {
        this.socialOauth = socialOauth;
    }
}
