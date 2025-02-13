package com.healthree.healthree_back.payment.model.type;

public enum CardOwnerType {
    개인(0), 법인(1);

    private Integer code;

    CardOwnerType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CardOwnerType of(Integer code) {
        for (CardOwnerType cardOwnerType : CardOwnerType.values()) {
            if (cardOwnerType.getCode().equals(code)) {
                return cardOwnerType;
            }
        }
        return null;
    }
}
