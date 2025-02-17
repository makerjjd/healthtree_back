package com.healthree.healthree_back.hosiptal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.hosiptal.model.dto.DoctorDto;
import com.healthree.healthree_back.hosiptal.model.dto.HospitalDetailDto;
import com.healthree.healthree_back.hosiptal.model.entity.DoctorEntity;
import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public HospitalDetailDto getHospital(Long id) {
        HospitalEntity hospitalEntity = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));
        List<DoctorEntity> doctorEntities = doctorRepository.findAllByHospitalId(id);
        List<DoctorDto> doctors = doctorEntities.stream().map(DoctorDto::new).collect(Collectors.toList());
        return new HospitalDetailDto(hospitalEntity, doctors);
    }
}
