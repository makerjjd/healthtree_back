package com.healthree.healthree_back.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.reservation.model.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

}
