package com.healthree.healthree_back.shopping.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "order_item")
@Entity
public class OrderItemEntity {
    private Long id;
    private Long orderId;
    private Long itemId;
    private Integer count;
    private Integer price;
}
