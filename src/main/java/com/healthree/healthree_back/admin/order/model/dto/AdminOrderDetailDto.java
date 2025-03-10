package com.healthree.healthree_back.admin.order.model.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.healthree.healthree_back.order.model.entity.UserOrderEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrderDetailDto {
    private Long id;
    private Long userId;
    private String status;
    private String orderDateTime;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private Integer totalPrice;
    private Integer deliveryPrice;
    private Integer finalPrice;
    private String deliveryId;
    private List<AdminOrderItemDto> products;

    public AdminOrderDetailDto(UserOrderEntity userOrderEntity, List<AdminOrderItemDto> products) {
        this.id = userOrderEntity.getId();
        this.userId = userOrderEntity.getUserId();
        this.status = userOrderEntity.getStatus().name();
        this.orderDateTime = userOrderEntity.getOrderDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.receiverName = userOrderEntity.getReceiverName();
        this.receiverPhone = userOrderEntity.getReceiverPhone();
        this.receiverAddress = userOrderEntity.getReceiverAddress();
        this.totalPrice = userOrderEntity.getTotalPrice();
        this.deliveryPrice = userOrderEntity.getDeliveryPrice();
        this.finalPrice = userOrderEntity.getFinalPrice();
        this.deliveryId = userOrderEntity.getDeliveryId();

        this.products = products;
    }
}
