package com.healthree.healthree_back.user.auth.google;

import com.healthree.healthree_back.user.model.entity.UserEntity;
import com.healthree.healthree_back.user.model.type.SocialLoginType;
import com.healthree.healthree_back.user.model.type.SocialUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//구글(서드파티)로 액세스 토큰을 보내 받아올 구글에 등록된 사용자 정보
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUser implements SocialUser {
    public String id;
    public String email;
    public Boolean verifiedEmail;
    public String name;
    public String givenName;
    public String familyName;
    public String picture;
    public String locale;

    @Override
    public UserEntity toEntity() {
        return UserEntity.builder()
                .socialId(getSocialId())
                .email(this.email)
                .name(this.name)
                .socialType(SocialLoginType.GOOGLE.name().toLowerCase())
                .build();
    }

    @Override
    public String getSocialId() {
        return this.id;
    }
}
