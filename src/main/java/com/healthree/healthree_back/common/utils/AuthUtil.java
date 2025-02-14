package com.healthree.healthree_back.common.utils;

import org.springframework.security.core.Authentication;

import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.config.auth.PrincipalDetails;
import com.healthree.healthree_back.user.model.entity.UserEntity;

public class AuthUtil {
    public static UserEntity getUserEntity(Authentication authentication) {
        UserEntity userEntity = null;

        try {
            PrincipalDetails details = (PrincipalDetails) authentication.getPrincipal();
            userEntity = details.getUser();
        } catch (Exception e) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVALID_TOKEN, "user parsing error");
        }

        return userEntity;
    }
}
