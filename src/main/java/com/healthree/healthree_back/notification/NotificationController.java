package com.healthree.healthree_back.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.common.utils.AuthUtil;
import com.healthree.healthree_back.notification.model.dto.NotificationResponseDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "병원 예약 도메인", description = "병원 예약 도메인")
@Slf4j
@RestController
@RequestMapping("/app/notification")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("")
    public ResponseEntity<?> getNotifications(Authentication authentication, PageRequestDto pageRequestDto) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        NotificationResponseDto notificationResponseDto = notificationService.getNotifications(userEntity,
                pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", notificationResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
