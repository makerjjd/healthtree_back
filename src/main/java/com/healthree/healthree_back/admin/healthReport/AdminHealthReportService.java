package com.healthree.healthree_back.admin.healthReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.admin.healthReport.model.dto.AdminHealthProductForIssueDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.AdminHospitalForIssueDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.GetHealthReportResponseDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.GetHospitalResponseForReport;
import com.healthree.healthree_back.admin.healthReport.model.dto.GetProductResponseForReport;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthForIssueDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthIssueItemDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthReportDetailDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthReportDetailProjection;
import com.healthree.healthree_back.admin.healthReport.model.dto.HealthReportDto;
import com.healthree.healthree_back.admin.healthReport.model.dto.UpdateHealthReportRequestDto;
import com.healthree.healthree_back.admin.product.model.ProductDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.healthReport.HealthProductForIssueRepository;
import com.healthree.healthree_back.healthReport.HealthReportRepository;
import com.healthree.healthree_back.healthReport.HospitalForIssueRepository;
import com.healthree.healthree_back.healthReport.model.entity.HealthProductForIssueEntity;
import com.healthree.healthree_back.healthReport.model.entity.HealthReportEntity;
import com.healthree.healthree_back.healthReport.model.entity.HospitalForIssueEntity;
import com.healthree.healthree_back.hosiptal.HospitalRepository;
import com.healthree.healthree_back.hosiptal.model.dto.HospitalDto;
import com.healthree.healthree_back.hosiptal.model.entity.HospitalEntity;
import com.healthree.healthree_back.shopping.ShoppingItemRepository;
import com.healthree.healthree_back.shopping.model.dto.ShoppingItemDto;
import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminHealthReportService {
        private final HealthReportRepository healthReportRepository;
        private final HealthProductForIssueRepository healthProductForIssueRepository;
        private final HospitalForIssueRepository hospitalForIssueRepository;
        private final ShoppingItemRepository shoppingItemRepository;
        private final HospitalRepository hospitalRepository;

        @Transactional(readOnly = true)
        public GetHealthReportResponseDto getHealthReports(PageRequestDto pageRequestDto) {
                Page<HealthReportEntity> healthReportEntities = healthReportRepository
                                .findAll(pageRequestDto.getPageable());

                List<HealthReportDto> healthReportDtos = new ArrayList<>();
                healthReportEntities.forEach(healthReportEntity -> {
                        healthReportDtos.add(new HealthReportDto(healthReportEntity));
                });

                return new GetHealthReportResponseDto(healthReportDtos, healthReportEntities.getTotalElements(),
                                healthReportEntities.getTotalPages());
        }

        @Transactional(readOnly = true)
        public HealthReportDetailDto getHealthReport(Long id) {
                HealthReportDetailProjection healthReportDetailProjection = healthReportRepository.findDetailById(id)
                                .orElseThrow(
                                                () -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND,
                                                                "해당 건강보고서를 찾을 수 없습니다."));

                List<HealthProductForIssueEntity> healthProductForIssueEntities = healthProductForIssueRepository
                                .findAllByReportId(id);

                List<HospitalForIssueEntity> hospitalForIssueEntities = hospitalForIssueRepository
                                .findAllByReportId(id);

                List<Long> healthProductIds = new ArrayList<>();
                List<Long> hospitalIds = new ArrayList<>();

                healthProductForIssueEntities.forEach(healthProductForIssueEntity -> {
                        healthProductIds.addAll(healthProductForIssueEntity.getProductIds());
                });

                hospitalForIssueEntities.forEach(hospitalForIssueEntity -> {
                        hospitalIds.addAll(hospitalForIssueEntity.getHospitalIds());
                });

                List<ShoppingItemEntity> shoppingItemEntities = shoppingItemRepository.findAllByIdIn(healthProductIds);
                List<HospitalEntity> hospitalEntities = hospitalRepository.findAllByIdIn(hospitalIds);

                Map<Long, ShoppingItemEntity> shoppingItemEntityMap = shoppingItemEntities.stream()
                                .collect(Collectors.toMap(ShoppingItemEntity::getId, Function.identity()));

                Map<Long, HospitalEntity> hospitalEntityMap = hospitalEntities.stream()
                                .collect(Collectors.toMap(HospitalEntity::getId, Function.identity()));

                Map<String, HealthForIssueDto> healthForIssueDtoMap = new HashMap<>();

                for (HealthProductForIssueEntity healthProductForIssueEntity : healthProductForIssueEntities) {
                        HealthForIssueDto healthForIssueDto = healthForIssueDtoMap
                                        .getOrDefault(healthProductForIssueEntity.getIssueName(),
                                                        new HealthForIssueDto());

                        AdminHealthProductForIssueDto adminHealthProductForIssueDto = healthForIssueDto
                                        .getHealthProductDto();
                        if (adminHealthProductForIssueDto == null) {
                                adminHealthProductForIssueDto = new AdminHealthProductForIssueDto();
                        }

                        adminHealthProductForIssueDto.setDescription(healthProductForIssueEntity.getDescription());
                        adminHealthProductForIssueDto
                                        .setProductItemDtos(healthProductForIssueEntity.getProductIds().stream()
                                                        .map(shoppingItemEntityMap::get).map(ShoppingItemDto::new)
                                                        .collect(Collectors.toList()));

                        healthForIssueDto.setHealthProductDto(adminHealthProductForIssueDto);
                        healthForIssueDto.setIssueName(healthProductForIssueEntity.getIssueName());

                        healthForIssueDtoMap.put(healthProductForIssueEntity.getIssueName(), healthForIssueDto);
                }

                for (HospitalForIssueEntity hospitalForIssueEntity : hospitalForIssueEntities) {
                        HealthForIssueDto healthForIssueDto = healthForIssueDtoMap
                                        .getOrDefault(hospitalForIssueEntity.getIssueName(), new HealthForIssueDto());

                        AdminHospitalForIssueDto adminHospitalForIssueDto = healthForIssueDto.getHospitalDto();
                        if (adminHospitalForIssueDto == null) {
                                adminHospitalForIssueDto = new AdminHospitalForIssueDto();
                        }

                        adminHospitalForIssueDto.setDesciprtion(hospitalForIssueEntity.getDescription());
                        adminHospitalForIssueDto.setHospitalDtos(hospitalForIssueEntity.getHospitalIds().stream()
                                        .map(hospitalEntityMap::get).map(HospitalDto::new)
                                        .collect(Collectors.toList()));

                        healthForIssueDto.setHospitalDto(adminHospitalForIssueDto);
                        healthForIssueDto.setIssueName(hospitalForIssueEntity.getIssueName());

                        healthForIssueDtoMap.put(hospitalForIssueEntity.getIssueName(), healthForIssueDto);
                }

                List<HealthForIssueDto> healthForIssueDtos = new ArrayList<>(healthForIssueDtoMap.values());

                return new HealthReportDetailDto(healthReportDetailProjection, healthForIssueDtos);
        }

        @Transactional(readOnly = false)
        public void updateHealthReport(Long id, UpdateHealthReportRequestDto updateHealthReportRequestDto) {
                HealthReportEntity healthReportEntity = healthReportRepository.findById(id)
                                .orElseThrow(() -> new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND,
                                                "해당 건강보고서를 찾을 수 없습니다."));

                healthReportEntity.setDoctorNote(updateHealthReportRequestDto.getDoctorNote());

                List<HealthProductForIssueEntity> healthProductForIssueEntities = healthProductForIssueRepository
                                .findAllByReportId(id);

                List<HealthProductForIssueEntity> newHealthProductForIssueEntities = new ArrayList<>();

                List<HospitalForIssueEntity> hospitalForIssueEntities = hospitalForIssueRepository
                                .findAllByReportId(id);

                List<HospitalForIssueEntity> newHospitalForIssueEntities = new ArrayList<>();

                Map<String, HealthProductForIssueEntity> healthProductForIssueEntityMap = healthProductForIssueEntities
                                .stream().collect(Collectors.toMap(HealthProductForIssueEntity::getIssueName,
                                                Function.identity()));

                Map<String, HospitalForIssueEntity> hospitalForIssueEntityMap = hospitalForIssueEntities.stream()
                                .collect(Collectors.toMap(HospitalForIssueEntity::getIssueName, Function.identity()));

                for (HealthIssueItemDto healthIssueItemDto : updateHealthReportRequestDto.getHealthIssueItems()) {
                        String issueName = healthIssueItemDto.getIssueName();

                        HealthProductForIssueEntity healthProductForIssueEntity = healthProductForIssueEntityMap
                                        .getOrDefault(issueName, null);

                        if (healthProductForIssueEntity == null) {
                                healthProductForIssueEntity = new HealthProductForIssueEntity();
                                healthProductForIssueEntity.setReportId(id);
                                healthProductForIssueEntity.setIssueName(issueName);
                                healthProductForIssueEntity
                                                .setDescription(healthIssueItemDto.getProducts().getDescription());
                                healthProductForIssueEntity
                                                .setProductIds(healthIssueItemDto.getProducts().getProductIds());

                                newHealthProductForIssueEntities.add(healthProductForIssueEntity);
                        } else {
                                healthProductForIssueEntity
                                                .setDescription(healthIssueItemDto.getProducts().getDescription());
                                healthProductForIssueEntity
                                                .setProductIds(healthIssueItemDto.getProducts().getProductIds());

                                newHealthProductForIssueEntities.add(healthProductForIssueEntity);
                                healthProductForIssueEntityMap.remove(issueName);
                        }

                        HospitalForIssueEntity hospitalForIssueEntity = hospitalForIssueEntityMap
                                        .getOrDefault(issueName, null);

                        if (hospitalForIssueEntity == null) {
                                hospitalForIssueEntity = new HospitalForIssueEntity();
                                hospitalForIssueEntity.setReportId(id);
                                hospitalForIssueEntity.setIssueName(issueName);
                                hospitalForIssueEntity
                                                .setDescription(healthIssueItemDto.getHospitals().getDescription());
                                hospitalForIssueEntity
                                                .setHospitalIds(healthIssueItemDto.getHospitals().getHospitalIds());

                                newHospitalForIssueEntities.add(hospitalForIssueEntity);
                        } else {
                                hospitalForIssueEntity
                                                .setDescription(healthIssueItemDto.getHospitals().getDescription());
                                hospitalForIssueEntity
                                                .setHospitalIds(healthIssueItemDto.getHospitals().getHospitalIds());

                                newHospitalForIssueEntities.add(hospitalForIssueEntity);
                                hospitalForIssueEntityMap.remove(issueName);
                        }
                }

                healthProductForIssueEntityMap.values().forEach(healthProductForIssueEntity -> {
                        healthProductForIssueRepository.delete(healthProductForIssueEntity);
                });

                hospitalForIssueEntityMap.values().forEach(hospitalForIssueEntity -> {
                        hospitalForIssueRepository.delete(hospitalForIssueEntity);
                });

                hospitalForIssueRepository.saveAll(newHospitalForIssueEntities);
                healthProductForIssueRepository.saveAll(newHealthProductForIssueEntities);

                healthReportRepository.save(healthReportEntity);
        }

        @Transactional(readOnly = true)
        public GetProductResponseForReport getProducts(PageRequestDto pageRequestDto) {
                String keyword = pageRequestDto.getSearch();

                Slice<ShoppingItemEntity> shoppingItemEntities = null;

                if (StringUtils.isNotBlank(keyword)) {
                        shoppingItemEntities = shoppingItemRepository.findByTitleContaining(keyword,
                                        pageRequestDto.getPageable());
                } else {
                        shoppingItemEntities = shoppingItemRepository.findAll(pageRequestDto.getPageable());
                }

                List<ProductDto> productDtos = shoppingItemEntities.stream().map(ProductDto::new)
                                .collect(Collectors.toList());

                String nextUrl = null;
                if (shoppingItemEntities != null && shoppingItemEntities.hasNext()) {
                        nextUrl = "/admin/healthReports/products" + pageRequestDto.nextUrl();
                }

                return new GetProductResponseForReport(productDtos, nextUrl);
        }

        @Transactional(readOnly = true)
        public GetHospitalResponseForReport getHospitals(PageRequestDto pageRequestDto) {
                String keyword = pageRequestDto.getSearch();

                Slice<HospitalEntity> hospitalEntities = null;

                if (StringUtils.isNotBlank(keyword)) {
                        hospitalEntities = hospitalRepository.findByNameContaining(keyword,
                                        pageRequestDto.getPageable());
                } else {
                        hospitalEntities = hospitalRepository.findAll(pageRequestDto.getPageable());
                }

                List<HospitalDto> hospitalDtos = hospitalEntities.stream().map(HospitalDto::new)
                                .collect(Collectors.toList());

                String nextUrl = null;
                if (hospitalEntities != null && hospitalEntities.hasNext()) {
                        nextUrl = "/admin/healthReports/hospitals" + pageRequestDto.nextUrl();
                }

                return new GetHospitalResponseForReport(hospitalDtos, nextUrl);
        }
}
