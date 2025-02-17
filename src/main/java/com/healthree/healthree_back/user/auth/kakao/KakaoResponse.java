package com.healthree.healthree_back.user.auth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthree.healthree_back.user.model.entity.UserEntity;
import com.healthree.healthree_back.user.model.type.SocialLoginType;
import com.healthree.healthree_back.user.model.type.SocialUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoResponse implements SocialUser {
    @JsonProperty("id")
    Long id;

    @JsonProperty("has_signed_up")
    Boolean hasSignedUp;

    @JsonProperty("kakao_account")
    KakaoAccountResponse kakaoAccount;

    @Override
    public String getSocialId() {
        return this.id.toString();
    }

    @Override
    public UserEntity toEntity() {
        return UserEntity.builder()
                .socialId(getSocialId())
                .email(this.kakaoAccount.email)
                .name(this.kakaoAccount.getName())
                .socialType(SocialLoginType.KAKAO.name().toLowerCase())
                .name(this.kakaoAccount.name)
                // .gender(GenderType.fromCode(this.kakaoAccount.gender))
                // .birthday(this.kakaoAccount.birthYear + this.kakaoAccount.birthday)
                // .imageUrl(this.kakaoAccount.profile.profileImageUrl)
                .phoneNumber(this.kakaoAccount.phoneNumber)
                .build();
    }
}