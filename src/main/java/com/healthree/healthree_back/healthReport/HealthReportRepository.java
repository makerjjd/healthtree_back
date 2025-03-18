package com.healthree.healthree_back.healthReport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.healthReport.model.entity.HealthReportEntity;

public interface HealthReportRepository extends JpaRepository<HealthReportEntity, Long> {

    Optional<HealthReportEntity> findTopByUserIdOrderByReportDateDesc(Long userId);

    List<HealthReportEntity> findAllByUserIdOrderByReportDateDesc(Long id);

    Optional<HealthReportEntity> findByCheckUpIdAndUserId(String checkUpId, Long id);

    // Page<HealthReportEntity> findAllOrderByReportDateDesc(Pageable pageable);

}
