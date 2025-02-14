package com.healthree.healthree_back.healthReport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.healthReport.model.entity.HealthProductForIssueEntity;

public interface HealthProductForIssueRepository extends JpaRepository<HealthProductForIssueEntity, Long> {

    List<HealthProductForIssueEntity> findAllByReportId(Long reportId);

}
