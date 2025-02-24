package com.healthree.healthree_back.reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.my.dto.projection.HospitalReservationSummaryProjection;
import com.healthree.healthree_back.reservation.model.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

        @Query(value = "SELECT r.id as id, r.confirm_date as reservationDateTime, h.name as hospitalName, d.name as doctorName, r.reservation_date_times as reservationRequestDateTimes FROM reservation r LEFT JOIN hospital h on h.id = r.hospital_id LEFT JOIN doctor d on d.id = r.doctor_id where r.is_deleted = 0 AND r.user_id = :id AND r.confirm_date > :now ORDER BY r.confirm_date DESC", nativeQuery = true)
        List<HospitalReservationSummaryProjection> findAllByUserIdAndReservationDateTimeAfterOrderByReservationDateTimeDesc(
                        Long id, LocalDateTime now);

        @Query(value = "SELECT r.id as id, r.confirm_date as reservationDateTime, h.name as hospitalName, d.name as doctorName, r.reservation_date_times as reservationRequestDateTimes FROM reservation r LEFT JOIN hospital h on h.id = r.hospital_id LEFT JOIN doctor d on d.id = r.doctor_id where r.is_deleted = 0 AND r.user_id = :id AND (r.confirm_date <= :now OR r.confirm_date IS NULL) ORDER BY r.confirm_date DESC LIMIT 3", nativeQuery = true)
        List<HospitalReservationSummaryProjection> findTop3ByUserIdAndReservationDateTimeBeforeOrderByReservationDateTimeDesc(
                        Long id, LocalDateTime now);

        @Query(value = "SELECT r.id as id, r.confirm_date as reservationDateTime, h.name as hospitalName, d.name as doctorName, r.reservation_date_times as reservationRequestDateTimes FROM reservation r LEFT JOIN hospital h on h.id = r.hospital_id LEFT JOIN doctor d on d.id = r.doctor_id where r.is_deleted = 0 AND r.user_id = :id ORDER BY r.confirm_date DESC", nativeQuery = true, countQuery = "SELECT count(*) FROM reservation r where r.user_id = :id")
        Slice<HospitalReservationSummaryProjection> findReservationInfoByUserIdOrderByConfirmDateDesc(Long id,
                        Pageable pageable);

        @Query(value = "SELECT r.id as id, r.confirm_date as reservationDateTime, h.name as hospitalName, d.name as doctorName, r.reservation_date_times as reservationRequestDateTimes FROM reservation r LEFT JOIN hospital h on h.id = r.hospital_id LEFT JOIN doctor d on d.id = r.doctor_id where r.is_deleted = 0 AND r.id = :id AND r.user_id = :userId", nativeQuery = true)
        Optional<HospitalReservationSummaryProjection> findReservationInfoByIdAndUserId(Long id, Long userId);

        Optional<ReservationEntity> findByIdAndUserId(Long id, Long id2);

}
