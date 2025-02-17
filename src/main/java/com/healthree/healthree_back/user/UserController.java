package com.healthree.healthree_back.user;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.user.auth.GetSocialOAuthRes;
import com.healthree.healthree_back.user.auth.OAuthService;
import com.healthree.healthree_back.user.model.dto.LoginTempRequestDto;
import com.healthree.healthree_back.user.model.dto.TokenDto;
import com.healthree.healthree_back.user.model.type.SocialLoginType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자 도메인", description = "사용자 도메인")
@Slf4j
@RestController
@RequestMapping("/app/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final OAuthService oAuthService;

    @Operation(summary = "임시로그인", description = "임시로그인")
    @PostMapping("/loginTemp")
    public ResponseEntity<?> signup(@RequestBody LoginTempRequestDto loginTempRequestDto) {
        TokenDto tokenDto = userService.loginTemp(loginTempRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", tokenDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "SNS 로그인을 위한 URL 요청", description = "client id를 포함한 URL 리턴")
    @ApiResponse(description = "SNS 로그인 URL", responseCode = "200")
    @ResponseBody
    @GetMapping(value = "/auth/{socialLoginType}/request")
    public ResponseEntity<?> socialLoginRequest(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
        String url = oAuthService.oAuthLoginRequest(socialLoginType);

        ApiResponseMessage message = ApiResponseMessage.successWithData("", url);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "SNS 로그인", description = "redirect url로 받은 코드를 넘겨 사용자 정보 획득")
    @ApiResponse(description = "사용자 정보", responseCode = "200")
    @ResponseBody
    @GetMapping(value = "/auth/{socialLoginType}/login")
    public ResponseEntity<?> socialLogin(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code) throws IOException, HealthTreeApplicationExceptionHandler {
        GetSocialOAuthRes getSocialOAuthRes = oAuthService.oAuthLoginOrJoin(socialLoginType, code);

        ApiResponseMessage message = ApiResponseMessage.successWithData("", getSocialOAuthRes);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
