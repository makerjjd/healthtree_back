package com.healthree.healthree_back.healthReport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.healthReport.model.dto.HealthProductForIssueDto;
import com.healthree.healthree_back.healthReport.model.dto.HealthReportHistoryResponseDto;
import com.healthree.healthree_back.healthReport.model.dto.HealthReportHistoryitemDto;
import com.healthree.healthree_back.healthReport.model.dto.HealthReportHomeResponseDto;
import com.healthree.healthree_back.healthReport.model.dto.HospitalForIssueDto;
import com.healthree.healthree_back.healthReport.model.entity.HealthProductForIssueEntity;
import com.healthree.healthree_back.healthReport.model.entity.HealthReportEntity;
import com.healthree.healthree_back.healthReport.model.entity.HospitalForIssueEntity;
import com.healthree.healthree_back.hosiptal.HospitalRepository;
import com.healthree.healthree_back.hosiptal.model.dto.HospitalDto;
import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;
import com.healthree.healthree_back.shopping.ShoppingItemRepository;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDto;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HealthReportService {
    private final HealthReportRepository healthReportRepository;
    private final HealthProductForIssueRepository healthProductForIssueRepository;
    private final HospitalForIssueRepository hospitalForIssueRepository;

    private final ShoppingItemRepository shoppingItemRepository;
    private final HospitalRepository hospitalRepository;

    @Transactional(readOnly = true)
    public HealthReportHomeResponseDto home(UserEntity userEntity) {
        // healthReport에서 최신의 하나의 report를 가져옴
        // Doctor note
        HealthReportEntity healthReportEntity = healthReportRepository
                .findTopByUserIdOrderByReportDateDesc(userEntity.getId())
                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND,
                        "health report not found"));

        // healthProduct
        List<HealthProductForIssueEntity> healthProductForIssueEntities = healthProductForIssueRepository
                .findAllByReportId(healthReportEntity.getId());

        List<HealthProductForIssueDto> healthProductForIssueDto = getShoppingItems(
                healthProductForIssueEntities);

        // hospital
        List<HospitalForIssueEntity> hospitalForIssueEntities = hospitalForIssueRepository
                .findAllByReportId(healthReportEntity.getId());

        List<HospitalForIssueDto> hospitalForIssueDtos = getHospitalForIssueDtos(hospitalForIssueEntities);

        return HealthReportHomeResponseDto.builder().doctorNote(healthReportEntity.getDoctorNote())
                .healthProductForIssueDtos(healthProductForIssueDto).hospitalForIssueDtos(hospitalForIssueDtos)
                .build();
    }

    private List<HealthProductForIssueDto> getShoppingItems(
            List<HealthProductForIssueEntity> healthProductForIssueEntities) {

        List<HealthProductForIssueDto> healthProductForIssueDtos = new ArrayList<>();

        healthProductForIssueEntities.forEach(healthProductForIssueEntity -> {
            List<ShoppingItemEntity> shoppingItemEntities = shoppingItemRepository
                    .findAllByIdIn(healthProductForIssueEntity.getProductIds());

            List<ShoppingItemDto> shoppingItemDtos = new ArrayList<>();
            shoppingItemEntities.forEach(shoppingItemEntity -> {
                shoppingItemDtos.add(new ShoppingItemDto(shoppingItemEntity));
            });

            HealthProductForIssueDto healthProductForIssueDto = new HealthProductForIssueDto();
            healthProductForIssueDto.setIssueName(healthProductForIssueEntity.getIssueName());
            healthProductForIssueDto.setProductItemDtos(shoppingItemDtos);

            healthProductForIssueDtos.add(healthProductForIssueDto);
        });

        return healthProductForIssueDtos;
    }

    private List<HospitalForIssueDto> getHospitalForIssueDtos(
            List<HospitalForIssueEntity> hospitalForIssueEntities) {

        List<HospitalForIssueDto> hospitalForIssueDtos = new ArrayList<>();

        hospitalForIssueEntities.forEach(hospitalForIssueEntity -> {
            List<HospitalEntity> hospitalEntities = hospitalRepository
                    .findAllByIdIn(hospitalForIssueEntity.getHospitalIds());

            List<HospitalDto> hospitalDtos = new ArrayList<>();
            hospitalEntities.forEach(hospitalEntity -> {
                hospitalDtos.add(new HospitalDto(hospitalEntity));
            });

            HospitalForIssueDto hospitalForIssueDto = new HospitalForIssueDto();
            hospitalForIssueDto.setIssueName(hospitalForIssueEntity.getIssueName());
            hospitalForIssueDto.setHospitalDtos(hospitalDtos);

            hospitalForIssueDtos.add(hospitalForIssueDto);
        });

        return hospitalForIssueDtos;
    }

    @Transactional(readOnly = true)
    public HealthReportHistoryResponseDto getHealthReportHistory(UserEntity userEntity) {
        List<HealthReportEntity> healthReportEntities = healthReportRepository
                .findAllByUserIdOrderByReportDateDesc(userEntity.getId());

        List<HealthReportHistoryitemDto> healthReportHistoryitemDtos = new ArrayList<>();
        healthReportEntities.forEach(healthReportEntity -> {
            healthReportHistoryitemDtos.add(new HealthReportHistoryitemDto(healthReportEntity));
        });

        return new HealthReportHistoryResponseDto(healthReportHistoryitemDtos);
    }
}
