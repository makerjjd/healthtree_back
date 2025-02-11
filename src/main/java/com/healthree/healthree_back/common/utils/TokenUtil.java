package com.healthree.healthree_back.common.utils;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.config.auth.PrincipalDetails;
import com.healthree.healthree_back.config.jwt.JwtProperties;
import com.healthree.healthree_back.user.model.dto.TokenDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class TokenUtil {
    public static TokenDto generateToken(UserEntity user) {
        // access token 암호화
        String accessToken = makeAccessToken(user);

        // Refresh Token 암호화
        String refreshToken = makeRefreshToken(user);

        TokenDto token = TokenDto.builder().accessToken(accessToken)
                .refreshToken(refreshToken)
                .grantType(JwtProperties.TOKEN_PREFIX).build();

        return token;
    }

    public static Boolean tokenValidation(String token) {
        try {
            JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                    .getClaim("tokenId").asString();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public static String decodeAccessToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                    .verify(token)
                    .getClaim("tokenId").asString();
        } catch (Exception e) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }

    public static String decodeAccessTokenBeforeLogin(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                    .verify(token)
                    .getClaim("mobileNumber").asString();
        } catch (Exception e) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }

    public static Long decodeRefreshToken(String refreshToken) {
        try {
            return Long.parseLong(JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                    .verify(refreshToken)
                    .getClaim("userId").asString());
        } catch (Exception e) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }

    public static String makeAccessTokenBeforeLogin(String mobileNumber) {
        return JWT.create()
                .withSubject(mobileNumber)
                .withClaim("mobileNumber", mobileNumber)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public static String makeAccessToken(UserEntity user) {
        String tokenId = user.getEmail();

        return JWT.create()
                .withSubject(tokenId)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("tokenId", tokenId)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public static String makeRefreshToken(UserEntity user) {
        String userId = user.getId().toString();

        return JWT.create()
                .withSubject(userId)
                .withClaim("userId", userId)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public static Authentication makeAuthentication(UserEntity userEntity) {
        PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principalDetails,
                null,
                principalDetails.getAuthorities());

        return authentication;

    }
}
