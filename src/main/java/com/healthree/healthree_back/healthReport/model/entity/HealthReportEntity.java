package com.healthree.healthree_back.healthReport.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "health_report")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthReport {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String reportId;

    @Column(nullable = false)
    private String hospitalName;

    @Column(nullable = false)
    private String dockerName;

    @Column(nullable = false)
    private String dockerNote;

    @Column(nullable = false)
    private LocalDate reportDate;
}
