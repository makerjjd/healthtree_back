package com.healthree.healthree_back.healthReport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.healthReport.model.entity.HospitalForIssueEntity;

public interface HospitalForIssueRepository extends JpaRepository<HospitalForIssueEntity, Long> {

    List<HospitalForIssueEntity> findAllByReportId(Long reportId);

}
