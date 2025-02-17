package com.healthree.healthree_back.user.auth.naver;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthree.healthree_back.user.model.type.SocialOauth;
import com.healthree.healthree_back.user.model.type.SocialUser;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Component
@RequiredArgsConstructor
public class NaverOauth implements SocialOauth {
    private final NaverAccessKey accessKey;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public String getAccessToken(String code) {
        accessKey.setCode(code);

        // random state 부여
        String state = RandomStringUtils.randomAlphanumeric(10);
        accessKey.setState(state);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        Map<String, String> maps = objectMapper.convertValue(accessKey, new TypeReference<Map<String, String>>() {
        });

        parameters.setAll(maps);

        URI uri = UriComponentsBuilder.fromUriString("https://nid.naver.com/oauth2.0/token").build().toUri();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<NaverOAuthToken> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
                NaverOAuthToken.class);

        Optional<NaverOAuthToken> authTokenOptional = Optional.of(response.getBody());
        NaverOAuthToken authToken = authTokenOptional.orElseThrow();

        return authToken.getAccessToken();
    }

    @Override
    public SocialUser getUserInfo(String accessToken) throws HttpClientErrorException {
        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com/v1/nid/me").build().toUri();

        // header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<NaverProfileResponse> response = restTemplate.exchange(uri, HttpMethod.GET,
                request,
                NaverProfileResponse.class);

        Optional<NaverProfileResponse> profileResponseOptional = Optional.of(response.getBody());
        NaverProfileResponse profileResponse = profileResponseOptional.orElseThrow();

        if (!profileResponse.getResultCode().equals("00")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, profileResponse.getMessage());
        }

        return profileResponse.getResponse();
    }

    @Override
    public String getLoginRequestUrl() {
        return "";
    }

    @OverrideOnly
    public String getLoginRequestUrlForDev(String callbackUrl) {
        return "";
    }

    @Override
    public String getLogoutUrl() {
        return "";
    }

    @Override
    public String getAccessTokenForDev(String code, String callbackUrl) {
        return "";
    }

}
