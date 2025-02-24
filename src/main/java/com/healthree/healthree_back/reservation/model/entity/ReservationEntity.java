package com.healthree.healthree_back.reservation.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.healthree.healthree_back.common.model.converter.LocalDateTimeListConverter;

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
@SQLRestriction("is_deleted=0")
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
    @Convert(converter = LocalDateTimeListConverter.class)
    private List<LocalDateTime> reservationDateTimes;

    @Column(nullable = true)
    private LocalDateTime confirmDate;

    @Column(nullable = false)
    private Boolean isDeleted;
}
