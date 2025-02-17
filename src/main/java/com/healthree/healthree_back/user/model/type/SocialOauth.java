package com.healthree.healthree_back.user.model.type;

import org.springframework.web.client.HttpClientErrorException;

public interface SocialOauth {
    String getAccessToken(String code);

    String getAccessTokenForDev(String code, String callbackUrl);

    SocialUser getUserInfo(String accessToken) throws HttpClientErrorException;

    String getLoginRequestUrl();

    String getLoginRequestUrlForDev(String callbackUrl);

    String getLogoutUrl();
}
