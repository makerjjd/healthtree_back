package com.healthree.healthree_back.hosiptal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.hosiptal.model.entity.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    List<DoctorEntity> findAllByHospitalId(Long id);

}
