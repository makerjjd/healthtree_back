package com.healthree.healthree_back.reservation.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.healthree.healthree_back.common.model.converter.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long hospitalId;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> reservationDateTimes;

    @Column(nullable = true)
    private String confirmDate;
}
