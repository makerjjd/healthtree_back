package com.healthree.healthree_back.common.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.healthree.healthree_back.admin.user.model.dto.AdminTokenDto;
import com.healthree.healthree_back.admin.user.model.entity.AdminUserEntity;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.config.auth.AdminPrincipalDetails;
import com.healthree.healthree_back.config.auth.PrincipalDetails;
import com.healthree.healthree_back.config.jwt.JwtProperties;
import com.healthree.healthree_back.user.model.dto.TokenDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;

    public AdminTokenDto generateAdminToken(AdminUserEntity user) {
        // access token 암호화
        String accessToken = makeAdminAccessToken(user);

        AdminTokenDto token = AdminTokenDto.builder().accessToken(accessToken)
                .grantType(JwtProperties.TOKEN_PREFIX).role(user.getRole()).build();

        return token;
    }

    public String makeAdminAccessToken(AdminUserEntity user) {
        String tokenId = user.getEmail();

        return JWT.create()
                .withSubject(tokenId)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("tokenId", tokenId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public TokenDto generateToken(UserEntity user) {
        // access token 암호화
        String accessToken = makeAccessToken(user);

        // Refresh Token 암호화
        String refreshToken = makeRefreshToken(user);

        TokenDto token = TokenDto.builder().accessToken(accessToken)
                .refreshToken(refreshToken)
                .grantType(JwtProperties.TOKEN_PREFIX).build();

        return token;
    }

    public Boolean tokenValidation(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token)
                    .getClaim("tokenId").asString();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public String decodeAccessToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(secretKey)).build()
                    .verify(token)
                    .getClaim("tokenId").asString();
        } catch (Exception e) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }

    public String decodeAccessTokenBeforeLogin(String token) {
        try {
            String userId = JWT.require(Algorithm.HMAC512(secretKey)).build()
                    .verify(token)
                    .getClaim("userId").asString();
            return userId;
        } catch (Exception e) {
            log.error("Error decoding token: {}", e.getMessage());
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }

    public String makeAccessTokenBeforeLogin(UserEntity user) {
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("userId", user.getId().toString())
                .sign(Algorithm.HMAC512(secretKey));
        return token;
    }

    public Long decodeRefreshToken(String refreshToken) {
        try {
            return Long.parseLong(JWT.require(Algorithm.HMAC512(secretKey)).build()
                    .verify(refreshToken)
                    .getClaim("userId").asString());
        } catch (Exception e) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }

    public String makeAccessToken(UserEntity user) {
        String tokenId = user.getEmail();

        return JWT.create()
                .withSubject(tokenId)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("tokenId", tokenId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public String makeRefreshToken(UserEntity user) {
        String userId = user.getId().toString();

        return JWT.create()
                .withSubject(userId)
                .withClaim("userId", userId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public Authentication makeAuthentication(UserEntity userEntity) {
        PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principalDetails,
                null,
                principalDetails.getAuthorities());

        return authentication;

    }

    public Authentication makeAdminAuthentication(AdminUserEntity adminUserEntity) {
        AdminPrincipalDetails principalDetails = new AdminPrincipalDetails(adminUserEntity);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principalDetails,
                null,
                principalDetails.getAuthorities());

        return authentication;

    }
}
