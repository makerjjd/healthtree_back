package com.healthree.healthree_back.hosiptal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.hosiptal.model.dto.HospitalDetailDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "병원정보 도메인", description = "병원정보 도메인")
@Slf4j
@RestController
@RequestMapping("/app/hospital")
@AllArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getHospital(@PathVariable Long id) {
        HospitalDetailDto HospitalDetailDto = hospitalService.getHospital(id);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", HospitalDetailDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
