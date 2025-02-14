package com.healthree.healthree_back.hosiptal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    List<HospitalEntity> findAllByIdIn(List<Long> hospitalIds);

}
