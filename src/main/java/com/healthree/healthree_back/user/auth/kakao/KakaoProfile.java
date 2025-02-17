package com.healthree.healthree_back.user.auth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoProfile {
    @JsonProperty("nickname")
    String nickname;

    @JsonProperty("thumbnail_image_url")
    String thumbnailImageUrl;

    @JsonProperty("profile_image_url")
    String profileImageUrl;

    @JsonProperty("is_default_image")
    Boolean isDefaultImage;
}
