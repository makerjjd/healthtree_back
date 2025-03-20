package com.healthree.healthree_back.hosiptal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    List<HospitalEntity> findAllByIdIn(List<Long> hospitalIds);

    @Query(nativeQuery = true, value = "SELECT * FROM hospital", countQuery = "SELECT count(*) FROM hospital")
    Page<HospitalEntity> findWithDeleteAll(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM hospital WHERE id = :id")
    Optional<HospitalEntity> findWithDeleteById(Long id);

    Slice<HospitalEntity> findByNameContaining(String keyword, Pageable pageable);

}
