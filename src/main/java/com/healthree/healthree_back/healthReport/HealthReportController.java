package com.healthree.healthree_back.healthReport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.common.utils.AuthUtil;
import com.healthree.healthree_back.healthReport.model.dto.HealthReportHistoryResponseDto;
import com.healthree.healthree_back.healthReport.model.dto.HealthReportHomeResponseDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "건강정보 도메인", description = "건강정보 도메인")
@Slf4j
@RestController
@RequestMapping("/app/healthReport")
@AllArgsConstructor
public class HealthReportController {
    private final HealthReportService healthReportService;

    @GetMapping("/home")
    public ResponseEntity<?> home(Authentication authentication) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        HealthReportHomeResponseDto healthReportHomeResponseDto = healthReportService.home(userEntity);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", healthReportHomeResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<?> history(Authentication authentication) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        HealthReportHistoryResponseDto healthReportHistoryDto = healthReportService.getHealthReportHistory(userEntity);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", healthReportHistoryDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
