package com.healthree.healthree_back.payment.model.type;

public enum PaymentStatus {
    READY(0), COMPLETE(1), CANCEL(2), DEPOSIT_READY(3);

    Integer code;

    PaymentStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static PaymentStatus valueOf(Integer code) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
