package com.healthree.healthree_back.my.dto.projection;

import java.time.LocalDateTime;

public interface HospitalReservationSummaryProjection {
    String getHospitalName();

    String getDoctorName();

    LocalDateTime getReservationDateTime();
}
