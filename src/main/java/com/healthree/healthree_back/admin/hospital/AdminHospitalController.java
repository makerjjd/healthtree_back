package com.healthree.healthree_back.admin.hospital;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.admin.hospital.model.dto.CreateHospitalDetailDto;
import com.healthree.healthree_back.admin.hospital.model.dto.GetHospitalResponseDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.hosiptal.model.dto.HospitalDetailDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "어드민 병원 도메인", description = "어드민 병원 도메인")
@Slf4j
@RestController
@RequestMapping("/admin/hospitals")
@AllArgsConstructor
public class AdminHospitalController {
    private final AdminHospitalService hospitalService;

    @GetMapping("")
    public ResponseEntity<?> getHospitals(PageRequestDto pageRequestDto) {
        GetHospitalResponseDto getHospitalResponseDto = hospitalService.getHospitals(pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", getHospitalResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHospital(@PathVariable Long id) {
        HospitalDetailDto hospitalDto = hospitalService.getHospital(id);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", hospitalDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createHospital(@RequestBody CreateHospitalDetailDto createHospitalDetailDto) {
        hospitalService.createHospital(createHospitalDetailDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateHospital(@PathVariable Long id,
            @RequestBody CreateHospitalDetailDto createHospitalDetailDto) {
        hospitalService.updateHospital(id, createHospitalDetailDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggleHospital(@PathVariable Long id) {
        hospitalService.toggleHospital(id);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

}
