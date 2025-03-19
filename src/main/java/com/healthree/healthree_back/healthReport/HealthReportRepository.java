package com.healthree.healthree_back.healthReport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.admin.healthReport.model.dto.HealthReportDetailProjection;
import com.healthree.healthree_back.healthReport.model.entity.HealthReportEntity;

public interface HealthReportRepository extends JpaRepository<HealthReportEntity, Long> {

    Optional<HealthReportEntity> findTopByUserIdOrderByReportDateDesc(Long userId);

    List<HealthReportEntity> findAllByUserIdOrderByReportDateDesc(Long id);

    Optional<HealthReportEntity> findByCheckUpIdAndUserId(String checkUpId, Long id);

    @Query(nativeQuery = true, value = "SELECT h.id AS id, u.id AS userId, u.name AS userName, u.phone_number AS userPhoneNumber, u.birth AS userBirth, u.gender AS userGender, h.doctor_note AS doctorNote FROM health_report h JOIN user u ON h.user_id = u.id WHERE h.id = :id")
    Optional<HealthReportDetailProjection> findDetailById(Long id);

    // Page<HealthReportEntity> findAllOrderByReportDateDesc(Pageable pageable);

}
