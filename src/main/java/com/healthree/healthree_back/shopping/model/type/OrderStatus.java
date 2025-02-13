package com.healthree.healthree_back.shopping.model.type;

public enum OrderStatus {
    ORDERED(0), DELIVERING(1), DELIVERED(2), CANCELED(3);

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
