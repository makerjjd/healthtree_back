package com.healthree.healthree_back.order.model.dto;

import java.util.List;

import com.healthree.healthree_back.order.model.entity.UserOrderEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private List<OrderShoppingItemRequestDto> orderItems;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private Integer deliveryPrice;
    private Integer totalPrice;
    private Integer finalPrice;

    public UserOrderEntity toEntity(UserEntity userEntity) {
        return UserOrderEntity.builder()
                .userId(userEntity.getId())
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .deliveryPrice(deliveryPrice)
                .totalPrice(totalPrice)
                .finalPrice(finalPrice)
                .build();
    }
}
