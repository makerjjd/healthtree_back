package com.healthree.healthree_back.shopping.model.entity;

import java.time.LocalDateTime;

import com.healthree.healthree_back.shopping.model.type.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhone;

    @Column(nullable = false)
    private String receiverAddress;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private Integer deliveryPrice;

    @Column(nullable = false)
    private Integer finalPrice;

    @Column(nullable = false)
    private String deliveryId;

    @Column(nullable = true)
    private LocalDateTime deliveryDateTime;

    @Column(nullable = true)
    private String deliveryAddress;
}
