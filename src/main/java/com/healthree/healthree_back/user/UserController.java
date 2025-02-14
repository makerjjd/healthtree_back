package com.healthree.healthree_back.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.user.model.dto.LoginTempRequestDto;
import com.healthree.healthree_back.user.model.dto.TokenDto;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "임시로그인", description = "임시로그인")
    @PostMapping("/loginTemp")
    public ResponseEntity<?> signup(@RequestBody LoginTempRequestDto loginTempRequestDto) {
        TokenDto tokenDto = userService.loginTemp(loginTempRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", tokenDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
