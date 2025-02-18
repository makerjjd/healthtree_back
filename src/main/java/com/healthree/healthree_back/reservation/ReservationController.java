package com.healthree.healthree_back.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.common.utils.AuthUtil;
import com.healthree.healthree_back.reservation.model.dto.ReservationDto;
import com.healthree.healthree_back.reservation.model.dto.ReservationRequestDto;
import com.healthree.healthree_back.reservation.model.dto.ReservationResponseDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "병원 예약 도메인", description = "병원 예약 도메인")
@Slf4j
@RestController
@RequestMapping("/app/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    public ResponseEntity<?> reservation(Authentication authentication,
            @RequestBody ReservationRequestDto reservationRequestDto) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        reservationService.reservation(userEntity, reservationRequestDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getReservations(Authentication authentication, PageRequestDto pageRequestDto) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        ReservationResponseDto reservationResponseDto = reservationService.getReservations(userEntity, pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", reservationResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservation(Authentication authentication, Long id) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        ReservationDto reservationDto = reservationService.getReservation(userEntity, id);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", reservationDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelReservation(Authentication authentication, Long id) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        reservationService.cancelReservation(userEntity, id);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
}
