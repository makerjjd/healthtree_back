package com.healthree.healthree_back.payment.model.type;

public enum CardType {
    신용(0), 체크(1), 기프트(2);

    private Integer code;

    CardType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CardType of(Integer code) {
        for (CardType cardType : CardType.values()) {
            if (cardType.getCode().equals(code)) {
                return cardType;
            }
        }
        return null;
    }
}
