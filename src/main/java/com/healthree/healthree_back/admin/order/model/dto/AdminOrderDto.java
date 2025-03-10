package com.healthree.healthree_back.admin.order.model.dto;

import java.time.format.DateTimeFormatter;

import com.healthree.healthree_back.order.model.entity.UserOrderEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrderDto {
    private Long id;
    private Long userId;
    private String status;
    private String orderDateTime;
    private Integer totalPrice;
    private Boolean isDeleted;

    public AdminOrderDto(UserOrderEntity userOrderEntity) {
        this.id = userOrderEntity.getId();
        this.userId = userOrderEntity.getUserId();
        this.status = userOrderEntity.getStatus().name();
        this.orderDateTime = userOrderEntity.getOrderDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.totalPrice = userOrderEntity.getTotalPrice();
        this.isDeleted = userOrderEntity.isDeleted();
    }
}
