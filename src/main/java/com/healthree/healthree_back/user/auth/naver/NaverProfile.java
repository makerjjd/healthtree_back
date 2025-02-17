package com.healthree.healthree_back.user.auth.naver;

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
public class NaverProfile implements SocialUser {
    @JsonProperty("id")
    private String id;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("age")
    private String age;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("birthyear")
    private String birthyear;

    @JsonProperty("mobile")
    private String mobile;

    @Override
    public String getSocialId() {
        return this.id;
    }

    @Override
    public UserEntity toEntity() {
        return UserEntity.builder()
                .socialId(getSocialId())
                .email(this.email)
                .name(this.getName())
                .socialType(SocialLoginType.NAVER.name().toLowerCase())
                .build();
    }

}
