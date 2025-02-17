package com.healthree.healthree_back.user.auth;

import com.healthree.healthree_back.user.model.dto.TokenDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(oneOf = { TokenDto.class })
public interface GetSocialOAuthRes {
}
