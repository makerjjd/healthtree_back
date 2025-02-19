package com.healthree.healthree_back.user.model.type;

import lombok.Getter;

@Getter
public enum GenderType {
    MALE(0, "남성"),
    FEMALE(1, "여성");

    GenderType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer code;
    private String description;

    public static GenderType valueOf(Integer code) {
        for (GenderType genderType : values()) {
            if (genderType.getCode().equals(code)) {
                return genderType;
            }
        }
        return null;
    }
}
