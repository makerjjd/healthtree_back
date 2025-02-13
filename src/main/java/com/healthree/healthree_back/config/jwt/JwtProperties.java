package com.healthree.healthree_back.config.jwt;

public interface JwtProperties {
    // application.yml에 설정한 jwt.secret값
    Long ACCESS_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 30; // 1 hour TODO: 1시간으로 변경
    Long REFRESH_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 30;// 30 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_ACCESS_STRING = "Authorization";
    String HEADER_REFRESH_STRING = "RefreshToken";
    String BEFORE_LOGIN_PREFIX = "_before_";
}
