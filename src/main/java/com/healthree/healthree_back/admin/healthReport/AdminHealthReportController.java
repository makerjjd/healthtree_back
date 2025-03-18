package com.healthree.healthree_back.admin.healthReport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.admin.healthReport.model.dto.GetHealthReportResponseDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthReportDetailDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "어드민 상품 도메인", description = "어드민 상품 도메인")
@Slf4j
@RestController
@RequestMapping("/admin/healthReports")
@AllArgsConstructor
public class AdminHealthReportController {
    private final AdminHealthReportService healthReportService;

    @GetMapping("")
    public ResponseEntity<?> getHealthReports(PageRequestDto pageRequestDto) {
        GetHealthReportResponseDto getHealthReportResponseDto = healthReportService.getHealthReports(pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", getHealthReportResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHealthReport(@PathVariable Long id) {
        HealthReportDetailDto healthReportDetailDto = healthReportService.getHealthReport(id);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", healthReportDetailDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
