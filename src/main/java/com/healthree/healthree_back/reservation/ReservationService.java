package com.healthree.healthree_back.reservation;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.my.dto.projection.HospitalReservationSummaryProjection;
import com.healthree.healthree_back.reservation.model.dto.ReservationDto;
import com.healthree.healthree_back.reservation.model.dto.ReservationRequestDto;
import com.healthree.healthree_back.reservation.model.dto.ReservationResponseDto;
import com.healthree.healthree_back.reservation.model.entity.ReservationEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = false)
    public void reservation(UserEntity userEntity, ReservationRequestDto reservationRequestDto) {
        reservationRepository.save(reservationRequestDto.toEntity(userEntity.getId()));
    }

    @Transactional(readOnly = true)
    public ReservationResponseDto getReservations(UserEntity userEntity, PageRequestDto pageRequestDto) {

        Slice<HospitalReservationSummaryProjection> reservationProjections = reservationRepository
                .findReservationInfoByUserIdOrderByConfirmDateDesc(userEntity.getId(), pageRequestDto.getPageable());

        List<ReservationDto> reservations = reservationProjections.getContent().stream()
                .map(reservation -> ReservationDto.toReservationDto(reservation))
                .toList();

        String nextUrl = null;
        if (reservationProjections.hasNext()) {
            nextUrl = "/app/reservations?page=" + (pageRequestDto.getPage() + 1);
        }

        return ReservationResponseDto.builder().reservations(reservations).nextUrl(nextUrl).build();

    }

    @Transactional(readOnly = true)
    public ReservationDto getReservation(UserEntity userEntity, Long id) {
        HospitalReservationSummaryProjection reservationProjection = reservationRepository
                .findReservationInfoByIdAndUserId(id, userEntity.getId())
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "예약 정보를 찾을 수 없습니다."));

        return ReservationDto.toReservationDto(reservationProjection);
    }

    @Transactional(readOnly = false)
    public void cancelReservation(UserEntity userEntity, Long id) {
        ReservationEntity reservationEntity = reservationRepository.findByIdAndUserId(id, userEntity.getId())
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "예약 정보를 찾을 수 없습니다."));
        reservationEntity.setIsDeleted(true);
        reservationRepository.save(reservationEntity);
    }
}
