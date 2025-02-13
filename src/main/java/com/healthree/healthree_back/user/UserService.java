package com.healthree.healthree_back.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.common.handler.HealthTreeApplicationExceptionHandler;
import com.healthree.healthree_back.common.model.ErrorCode;
import com.healthree.healthree_back.common.utils.TokenUtil;
import com.healthree.healthree_back.user.model.dto.LoginTempRequestDto;
import com.healthree.healthree_back.user.model.dto.TokenDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    @Transactional(readOnly = false)
    public TokenDto loginTemp(LoginTempRequestDto loginTempRequestDto) {
        UserEntity userEntity = userRepository
                .findByCheckUpIdAndNameAndPhoneNumber(loginTempRequestDto.getCheckUpId(),
                        loginTempRequestDto.getName(), loginTempRequestDto.getPhoneNumber())
                .orElseThrow(
                        () -> new HealthTreeApplicationExceptionHandler(ErrorCode.INVAILD_USER_INFO, "유저 정보가 없습니다."));

        tokenUtil.makeAccessTokenBeforeLogin(userEntity);

        return TokenDto.builder().accessToken("accessToken").refreshToken("refreshToken").build();
    }

}
