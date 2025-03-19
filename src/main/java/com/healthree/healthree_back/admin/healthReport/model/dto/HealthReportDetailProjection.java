package com.healthree.healthree_back.admin.healthReport.model.dto;

public interface HealthReportDetailProjection {
    Long getId();

    Long getUserId();

    String getUserName();

    String getUserPhoneNumber();

    String getUserBirth();

    Integer getUserGender();

    String getDoctorNote();
}
