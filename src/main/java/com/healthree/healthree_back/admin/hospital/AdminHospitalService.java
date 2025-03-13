package com.healthree.healthree_back.admin.hospital;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.admin.hospital.model.dto.CreateHospitalDetailDto;
import com.healthree.healthree_back.admin.hospital.model.dto.GetHospitalResponseDto;
import com.healthree.healthree_back.admin.hospital.model.dto.HospitalDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.hosiptal.DoctorRepository;
import com.healthree.healthree_back.hosiptal.HospitalRepository;
import com.healthree.healthree_back.hosiptal.model.dto.DoctorDto;
import com.healthree.healthree_back.hosiptal.model.dto.HospitalDetailDto;
import com.healthree.healthree_back.hosiptal.model.entity.DoctorEntity;
import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminHospitalService {
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public GetHospitalResponseDto getHospitals(PageRequestDto pageRequestDto) {

        Page<HospitalEntity> hospitals = hospitalRepository.findWithDeleteAll(pageRequestDto.getPageable());

        List<HospitalDto> hospitalDtos = hospitals.stream().map(HospitalDto::new).collect(Collectors.toList());

        return new GetHospitalResponseDto(hospitalDtos, hospitals.getTotalElements(), hospitals.getTotalPages());
    }

    @Transactional(readOnly = true)
    public HospitalDetailDto getHospital(Long id) {
        HospitalEntity hospitalEntity = hospitalRepository.findWithDeleteById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));

        List<DoctorEntity> doctorEntites = doctorRepository.findAllByHospitalId(id);
        List<DoctorDto> doctorDtos = doctorEntites.stream().map(DoctorDto::new).collect(Collectors.toList());

        return new HospitalDetailDto(hospitalEntity, doctorDtos);
    }

    @Transactional(readOnly = false)
    public void createHospital(CreateHospitalDetailDto createHospitalDetailDto) {
        HospitalEntity hospitalEntity = hospitalRepository.save(createHospitalDetailDto.toEntity());

        List<DoctorEntity> doctors = createHospitalDetailDto.getDoctors().stream()
                .map(doctorDto -> doctorDto.toEntity(hospitalEntity.getId()))
                .collect(Collectors.toList());

        doctorRepository.saveAll(doctors);
    }

    @Transactional(readOnly = false)
    public void updateHospital(Long id, CreateHospitalDetailDto createHospitalDetailDto) {
        HospitalEntity hospitalEntity = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));
        hospitalEntity.setName(createHospitalDetailDto.getName());
        hospitalEntity.setAddress(createHospitalDetailDto.getAddress());
        hospitalEntity.setPhone(createHospitalDetailDto.getPhoneNumber());
        hospitalEntity.setDescription(createHospitalDetailDto.getDescription());

        List<DoctorEntity> doctors = doctorRepository.findAllByHospitalId(id);

        List<DoctorEntity> newDoctors = createHospitalDetailDto.getDoctors().stream()
                .map(doctorDto -> doctorDto.toEntity(hospitalEntity.getId()))
                .collect(Collectors.toList());

        // doctors 중 newDoctors에 id가 포함되지 않는 것들은 삭제
        // newDoctors에서 id가 같이 없으면 doctors에 포함.
        Map<Long, DoctorEntity> doctorMap = doctors.stream()
                .collect(Collectors.toMap(DoctorEntity::getId, doctorEntity -> doctorEntity));

        for (DoctorEntity newDoctor : newDoctors) {
            Long doctorId = newDoctor.getId();

            if (doctorId == null) {
                doctors.add(newDoctor);
            } else {
                DoctorEntity doctorEntity = doctorMap.get(doctorId);
                if (doctorEntity != null) {
                    doctorEntity.setName(newDoctor.getName());
                    doctorEntity.setImage(newDoctor.getImage());
                    doctorEntity.setDescription(newDoctor.getDescription());
                    doctorMap.remove(doctorId);
                } else {
                    doctors.add(newDoctor);
                }
            }
        }

        for (DoctorEntity doctorEntity : doctorMap.values()) {
            doctorEntity.setDeleted(true);
        }

        doctorRepository.saveAll(doctors);

        hospitalRepository.save(hospitalEntity);
    }

    @Transactional(readOnly = false)
    public void toggleHospital(Long id) {
        HospitalEntity hospitalEntity = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));
        hospitalEntity.setDeleted(!hospitalEntity.isDeleted());
        hospitalRepository.save(hospitalEntity);
    }

}
