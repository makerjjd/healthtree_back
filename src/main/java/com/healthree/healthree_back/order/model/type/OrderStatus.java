package com.healthree.healthree_back.order.model.type;

public enum OrderStatus {
    ORDERED(0), PAYMENT_COMPLETED(1), DELIVERING(2), DELIVERED(3), CANCELED(4), CANCEL_REQUESTED(5),
    REFUND_REQUESTED(6), REFUNDED(7);

    Integer code;

    OrderStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static OrderStatus valueOf(Integer code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
