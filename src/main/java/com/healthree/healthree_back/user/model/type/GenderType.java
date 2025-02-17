package com.healthree.healthree_back.user.model.type;

import lombok.Getter;

@Getter
public enum GenderType {
    MALE("MALE", "남성"),
    FEMALE("FEMALE", "여성");

    GenderType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;
    private String description;

    public static GenderType fromCode(String code) {
        for (GenderType gender : GenderType.values()) {
            if (code != null && gender.getCode().equals(code.toUpperCase())) {
                return gender;
            }
        }
        return null;
    }
}
