package com.healthree.healthree_back.admin.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrderItemDto {
    private Long id;
    private Long itemId;
    private String itemName;
    private Integer count;
    private Integer price;

    public AdminOrderItemDto(AdminOrderItemProjection adminOrderItemProjection) {
        this.id = adminOrderItemProjection.getId();
        this.itemId = adminOrderItemProjection.getItemId();
        this.itemName = adminOrderItemProjection.getItemName();
        this.count = adminOrderItemProjection.getCount();
        this.price = adminOrderItemProjection.getPrice();
    }
}
