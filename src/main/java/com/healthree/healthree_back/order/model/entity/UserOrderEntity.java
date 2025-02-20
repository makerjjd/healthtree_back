package com.healthree.healthree_back.order.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import com.healthree.healthree_back.order.model.type.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "user_order")
@SQLRestriction("is_deleted=0")
@Builder
public class UserOrderEntity {
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

    @Column(nullable = false)
    private boolean isDeleted;
}
