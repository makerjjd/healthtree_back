package com.healthree.healthree_back.user.auth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class KakaoAccountResponse {
    @JsonProperty("profile_needs_agreement")
    Boolean profileNeedsAgreement;

    @JsonProperty("profile_nickname_needs_agreement")
    Boolean profileNicknameNeedsAgreement;

    @JsonProperty("profile_image_needs_agreement")
    Boolean profileImageNeedsAgreement;

    @JsonProperty("profile")
    KakaoProfile profile;

    @JsonProperty("name_needs_agreement")
    Boolean nameNeedsAgreement;

    @JsonProperty("name")
    String name;

    @JsonProperty("email_needs_agreement")
    Boolean emailNeedsAgreement;

    @JsonProperty("is_email_valid")
    Boolean isEmailvalid;

    @JsonProperty("is_email_verified")
    Boolean isEmailverified;

    @JsonProperty("email")
    String email;

    @JsonProperty("age_range_needs_agreement")
    Boolean ageRangeNeedsAgreement;

    @JsonProperty("age_range")
    String ageRange;

    @JsonProperty("birthyear_needs_agreement")
    Boolean birthyearNeedsAgreement;

    @JsonProperty("birthyear")
    String birthYear;

    @JsonProperty("birthday_needs_agreement")
    Boolean birthdayNeedsAgreement;

    @JsonProperty("birthday")
    String birthday;

    @JsonProperty("birthday_type")
    String birthdayType;

    @JsonProperty("gender_needs_agreement")
    Boolean genderNeedsAgreement;

    @JsonProperty("gender")
    String gender;

    @JsonProperty("phone_number_needs_agreement")
    Boolean phoneNumberNeedsAgreement;

    @JsonProperty("phone_number")
    String phoneNumber;

    @JsonProperty("ci_needs_agreement")
    Boolean ciNeedsAgreement;

    @JsonProperty("ci")
    String ci;
}
