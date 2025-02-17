package com.healthree.healthree_back.user.auth;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.common.utils.TokenUtil;
import com.healthree.healthree_back.user.UserService;
import com.healthree.healthree_back.user.model.dto.GetUserRes;
import com.healthree.healthree_back.user.model.dto.TokenDto;
import com.healthree.healthree_back.user.model.type.SocialLoginType;
import com.healthree.healthree_back.user.model.type.SocialOauth;
import com.healthree.healthree_back.user.model.type.SocialUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OAuthService {
    private final UserService userService;
    private final TokenUtil tokenUtil;

    public GetSocialOAuthRes oAuthLoginOrJoin(SocialLoginType socialLoginType, String code) throws IOException {

        SocialOauth socialOauth = socialLoginType.getSocialOauth();

        String accessToken = socialOauth.getAccessToken(code);
        SocialUser socialUser = socialOauth.getUserInfo(accessToken);

        GetUserRes getUserRes = userService.getUserBySocialId(socialLoginType.getSocialLoginName(),
                socialUser.getSocialId());
        if (getUserRes == null) {
            getUserRes = userService.createOAuthUser(socialUser.toEntity());
        } else {
            // 회원 탈퇴이거나 정지된 회원일 경우 로그인 불가
            // if (UserStatusType.SUSPENDED.equals(getUserRes.getStatus())) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.INVAILD_USER_INFO, "정지된 회원입니다.");
            // }
        }

        // 서버에 user가 존재하면 앞으로 회원 인가 처리를 위한 jwtToken을 발급한다.
        return tokenUtil.generateToken(socialUser.toEntity());
        // 액세스 토큰과 jwtToken, 이외 정보들이 담긴 자바 객체를 다시 전송한다.
    }

    public String oAuthLoginRequest(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = socialLoginType.getSocialOauth();

        return socialOauth.getLoginRequestUrl();
    }

    public String oAuthLoginRequest(SocialLoginType socialLoginType, String callBackUrl) {
        SocialOauth socialOauth = socialLoginType.getSocialOauth();

        return socialOauth.getLoginRequestUrlForDev(callBackUrl);
    }

    public String logout(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = socialLoginType.getSocialOauth();
        return socialOauth.getLogoutUrl();
    }
}
