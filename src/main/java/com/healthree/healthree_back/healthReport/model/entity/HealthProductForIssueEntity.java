package com.healthree.healthree_back.healthReport.model.entity;

import java.util.List;

import com.healthree.healthree_back.common.model.converter.LongListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "health_product_for_issue")
@AllArgsConstructor
@NoArgsConstructor
public class HealthProductForIssueEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reportId;

    @Column(nullable = false)
    private String issueName;

    @Column(nullable = false)
    @Convert(converter = LongListConverter.class)
    private List<Long> productIds;

    @Column(nullable = false)
    private String description;
}
