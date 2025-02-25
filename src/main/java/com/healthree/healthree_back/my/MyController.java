package com.healthree.healthree_back.my;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.common.utils.AuthUtil;
import com.healthree.healthree_back.my.dto.MyHomeResponseDto;
import com.healthree.healthree_back.notification.model.dto.NotificationCartCountResponseDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "내정보 도메인", description = "내정보 도메인")
@Slf4j
@RestController
@RequestMapping("/app/my")
@AllArgsConstructor
public class MyController {
    private final MyService myService;

    @GetMapping("")
    public ResponseEntity<?> home(Authentication authentication) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        MyHomeResponseDto myHomeResponseDto = myService.home(userEntity);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", myHomeResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getNotificationCount(Authentication authentication) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        NotificationCartCountResponseDto notificationCountResponseDto = myService.getNotifiationCartCoun(userEntity);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", notificationCountResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
