package com.healthree.healthree_back.payment.model.type;

public enum PaymentMethod {
    카드(0), 퀵계좌이체(1), 가상계좌(2), 토스페이(3), 네이버페이(4), 삼성페이(5), 애플페이(6), 엘페이(7),
    카카오페이(8), 핀페이(9), 페이코(10), SSG페이(11), 계좌이체(12);

    Integer code;

    PaymentMethod(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static PaymentMethod valueOf(Integer code) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.getCode().equals(code)) {
                return method;
            }
        }
        return null;
    }
}
