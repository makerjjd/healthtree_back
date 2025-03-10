package com.healthree.healthree_back.admin.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.admin.user.model.dto.AdminTokenDto;
import com.healthree.healthree_back.admin.user.model.dto.AdminUserDto;
import com.healthree.healthree_back.admin.user.model.dto.AdminUserRequestDto;
import com.healthree.healthree_back.admin.user.model.dto.GetAdminUserResponseDto;
import com.healthree.healthree_back.admin.user.model.dto.GetUserDetailResponseDto;
import com.healthree.healthree_back.admin.user.model.dto.GetUserResponseDto;
import com.healthree.healthree_back.admin.user.model.dto.LoginRequestDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "어드민 사용자 도메인", description = "어드민 사용자 도메인")
@Slf4j
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    @Operation(summary = "어드민 로그인", description = "어드민 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        AdminTokenDto tokenDto = adminUserService.login(loginRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", tokenDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "사용자 목록", description = "사용자 목록")
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(PageRequestDto pageRequestDto) {
        GetUserResponseDto getUserResponseDto = adminUserService.getUsers(pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", getUserResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "사용자 상세", description = "사용자 상세")
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        GetUserDetailResponseDto getUserDetailResponseDto = adminUserService.getUser(userId);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", getUserDetailResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "사용자 삭제 토글", description = "사용자 삭제")
    @PatchMapping("/users/{userId}")
    public ResponseEntity<?> toggleDeleteUser(@PathVariable Long userId) {
        adminUserService.toggleDeleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "어드민 사용자 목록", description = "어드민 사용자 목록")
    @GetMapping("/adminUsers")
    public ResponseEntity<?> getAdminUsers(PageRequestDto pageRequestDto) {
        GetAdminUserResponseDto getAdminUserResponseDto = adminUserService.getAdminUsers(pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", getAdminUserResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "어드민 사용자 상세", description = "어드민 사용자 상세")
    @GetMapping("/adminUsers/{adminUserId}")
    public ResponseEntity<?> getAdminUser(@PathVariable Long adminUserId) {
        AdminUserDto adminUserDto = adminUserService.getAdminUser(adminUserId);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", adminUserDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "어드민 사용자 삭제 토글", description = "어드민 사용자 삭제")
    @PatchMapping("/adminUsers/{adminUserId}")
    public ResponseEntity<?> toggleDeleteAdminUser(@PathVariable Long adminUserId) {
        adminUserService.toggleDeleteAdminUser(adminUserId);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "어드민 사용자 추가", description = "어드민 사용자 추가")
    @PostMapping("/adminUsers")
    public ResponseEntity<?> addAdminUser(@RequestBody AdminUserRequestDto adminUserDto) {
        adminUserService.addAdminUser(adminUserDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @Operation(summary = "어드민 사용자 수정", description = "어드민 사용자 수정")
    @PatchMapping("/adminUsers/{adminUserId}")
    public ResponseEntity<?> updateAdminUser(@PathVariable Long adminUserId,
            @RequestBody AdminUserRequestDto adminUserDto) {
        adminUserService.updateAdminUser(adminUserId, adminUserDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

}
