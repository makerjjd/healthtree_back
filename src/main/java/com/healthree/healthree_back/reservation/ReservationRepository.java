package com.healthree.healthree_back.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.my.dto.projection.HospitalReservationSummaryProjection;
import com.healthree.healthree_back.reservation.model.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = "SELECT r.confirm_date as reservationDtateTime, h.name as hospitalName, d.name as doctorName FROM reservation r LEFT JOIN hospital h on h.id = r.hospital_id LEFT JOIN doctor d on d.id = r.doctor_id where r.user_id = :id AND r.confirm_date > :now ORDER BY r.confirm_date DESC", nativeQuery = true)
    List<HospitalReservationSummaryProjection> findAllByUserIdAndReservationDateTimeAfterOrderByReservationDateTimeDesc(
            Long id, LocalDateTime now);

    @Query(value = "SELECT r.confirm_date as reservationDtateTime, h.name as hospitalName, d.name as doctorName FROM reservation r LEFT JOIN hospital h on h.id = r.hospital_id LEFT JOIN doctor d on d.id = r.doctor_id where r.user_id = :id AND r.confirm_date <= :now ORDER BY r.confirm_date DESC LIMIT 3", nativeQuery = true)
    List<HospitalReservationSummaryProjection> findTop3ByUserIdAndReservationDateTimeBeforeOrderByReservationDateTimeDesc(
            Long id, LocalDateTime now);

}
