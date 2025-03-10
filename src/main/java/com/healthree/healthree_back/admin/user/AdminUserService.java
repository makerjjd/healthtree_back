package com.healthree.healthree_back.admin.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.admin.user.model.dto.AdminTokenDto;
import com.healthree.healthree_back.admin.user.model.dto.AdminUserDto;
import com.healthree.healthree_back.admin.user.model.dto.AdminUserRequestDto;
import com.healthree.healthree_back.admin.user.model.dto.GetAdminUserResponseDto;
import com.healthree.healthree_back.admin.user.model.dto.GetUserDetailResponseDto;
import com.healthree.healthree_back.admin.user.model.dto.GetUserResponseDto;
import com.healthree.healthree_back.admin.user.model.dto.LoginRequestDto;
import com.healthree.healthree_back.admin.user.model.dto.UserDto;
import com.healthree.healthree_back.admin.user.model.entity.AdminUserEntity;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.common.utils.TokenUtil;
import com.healthree.healthree_back.user.UserRepository;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    @Transactional(readOnly = true)
    public AdminTokenDto login(LoginRequestDto loginRequestDto) {
        AdminUserEntity adminUser = adminUserRepository.findByEmail(loginRequestDto.getEmail()).orElse(null);
        if (adminUser == null) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 이메일로 가입된 사용자가 없습니다.");
        }
        if (!adminUser.getPassword().equals(loginRequestDto.getPassword())) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.BAD_AGREEMENT_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        return tokenUtil.generateAdminToken(adminUser);
    }

    @Transactional(readOnly = true)
    public GetUserResponseDto getUsers(PageRequestDto pageRequestDto) {
        Page<UserEntity> userEntity = userRepository.findAllWithDelete(pageRequestDto.getPageable());

        List<UserDto> userDtos = userEntity.stream().map(UserDto::new).collect(Collectors.toList());

        return new GetUserResponseDto(userDtos, userEntity.getTotalPages());
    }

    @Transactional(readOnly = true)
    public GetUserDetailResponseDto getUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 사용자가 없습니다.");
        }

        return new GetUserDetailResponseDto(userEntity);
    }

    @Transactional(readOnly = false)
    public void toggleDeleteUser(Long userId) {
        UserEntity userEntity = userRepository.findWithDeleteById(userId).orElse(null);
        if (userEntity == null) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 사용자가 없습니다.");
        }

        if (userEntity.isDeleted()) {
            userEntity.setDeleted(false);
        } else {
            userEntity.setDeleted(true);
        }

        userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    public GetAdminUserResponseDto getAdminUsers(PageRequestDto pageRequestDto) {
        Page<AdminUserEntity> adminUserEntity = adminUserRepository.findWithDeleteAll(pageRequestDto.getPageable());

        List<AdminUserDto> userDtos = adminUserEntity.stream().map(AdminUserDto::new).collect(Collectors.toList());

        return new GetAdminUserResponseDto(userDtos, adminUserEntity.getTotalPages());
    }

    @Transactional(readOnly = true)
    public AdminUserDto getAdminUser(Long adminUserId) {
        AdminUserEntity adminUserEntity = adminUserRepository.findWithDeleteById(adminUserId).orElse(null);
        if (adminUserEntity == null) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 사용자가 없습니다.");
        }

        return new AdminUserDto(adminUserEntity);
    }

    @Transactional(readOnly = false)
    public void toggleDeleteAdminUser(Long adminUserId) {
        AdminUserEntity adminUserEntity = adminUserRepository.findWithDeleteById(adminUserId).orElse(null);
        if (adminUserEntity == null) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 사용자가 없습니다.");
        }

        if (adminUserEntity.isDeleted()) {
            adminUserEntity.setDeleted(false);
        } else {
            adminUserEntity.setDeleted(true);
        }

        adminUserRepository.save(adminUserEntity);
    }

    @Transactional(readOnly = false)
    public void addAdminUser(AdminUserRequestDto adminUserDto) {
        adminUserRepository.save(adminUserDto.toEntity());
    }

    @Transactional(readOnly = false)
    public void updateAdminUser(Long adminUserId, AdminUserRequestDto adminUserDto) {
        AdminUserEntity adminUserEntity = adminUserRepository.findWithDeleteById(adminUserId).orElse(null);
        if (adminUserEntity == null) {
            throw new HealthTreeApplicationExceptionHandler(ErrorCode.NOT_FOUND, "해당 사용자가 없습니다.");
        }

        adminUserEntity.setEmail(adminUserDto.getEmail());
        adminUserEntity.setPassword(adminUserDto.getPassword());
        adminUserEntity.setName(adminUserDto.getName());
        adminUserEntity.setPhoneNumber(adminUserDto.getPhoneNumber());
        adminUserEntity.setRole(adminUserDto.getRole());
        adminUserEntity.setStatus(adminUserDto.getStatus());

        adminUserRepository.save(adminUserEntity);
    }
}
