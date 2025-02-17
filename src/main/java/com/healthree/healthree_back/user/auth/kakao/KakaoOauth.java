package com.healthree.healthree_back.user.auth.kakao;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthree.healthree_back.user.model.type.SocialOauth;
import com.healthree.healthree_back.user.model.type.SocialUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOauth implements SocialOauth {

    private final KakaoAccessKey accessKey;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public String getAccessToken(String code) {
        accessKey.setCode(code);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        Map<String, String> maps = objectMapper.convertValue(accessKey, new TypeReference<Map<String, String>>() {
        });

        parameters.setAll(maps);

        URI uri = UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/token").build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<?> httpEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<KakaoOAuthToken> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
                KakaoOAuthToken.class);

        Optional<KakaoOAuthToken> authTokenOptional = Optional.of(response.getBody());
        KakaoOAuthToken authToken = authTokenOptional.orElseThrow();
        return authToken.getAccessToken();
    }

    @Override
    public String getAccessTokenForDev(String code, String callbackUrl) {
        accessKey.setCode(code);

        KakaoAccessKey newAccessKey = new KakaoAccessKey();
        newAccessKey.setGrantType(accessKey.getGrantType());
        newAccessKey.setClientId(accessKey.getClientId());
        newAccessKey.setRedirectUri(callbackUrl);
        newAccessKey.setSecretKey(accessKey.getSecretKey());
        newAccessKey.setCode(accessKey.getCode());
        newAccessKey.setLogoutRedirectUri(accessKey.getLogoutRedirectUri());

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        Map<String, String> maps = objectMapper.convertValue(newAccessKey, new TypeReference<Map<String, String>>() {
        });

        parameters.setAll(maps);

        URI uri = UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/token").build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<?> httpEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<KakaoOAuthToken> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
                KakaoOAuthToken.class);

        Optional<KakaoOAuthToken> authTokenOptional = Optional.of(response.getBody());
        KakaoOAuthToken authToken = authTokenOptional.orElseThrow();
        return authToken.getAccessToken();
    }

    @Override
    public SocialUser getUserInfo(String accessToken) {
        URI uri = UriComponentsBuilder.fromUriString("https://kapi.kakao.com/v2/user/me").build().toUri();

        // header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<KakaoResponse> response = restTemplate.exchange(uri, HttpMethod.GET,
                request,
                KakaoResponse.class);

        Optional<KakaoResponse> authTokenOptional = Optional.of(response.getBody());
        return authTokenOptional.orElseThrow();
    }

    @Override
    public String getLoginRequestUrl() {
        URI uri = UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", accessKey.getClientId())
                .queryParam("redirect_uri", accessKey.getRedirectUri())
                .build().toUri();

        return uri.toString();
    }

    @Override
    public String getLoginRequestUrlForDev(String callbackUrl) {
        URI uri = UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", accessKey.getClientId())
                .queryParam("redirect_uri", callbackUrl)
                .build().toUri();

        return uri.toString();
    }

    @Override
    public String getLogoutUrl() {
        URI uri = UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/logout")
                .queryParam("client_id", accessKey.getClientId())
                .queryParam("logout_redirect_uri", accessKey.getLogoutRedirectUri())
                .build().toUri();

        return uri.toString();
    }

}
