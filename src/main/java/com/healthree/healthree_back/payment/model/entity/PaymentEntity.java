package com.healthree.healthree_back.payment.model.entity;

import com.healthree.healthree_back.payment.model.type.PaymentMethod;
import com.healthree.healthree_back.payment.model.type.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "payment")
@Entity
public class PaymentEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String paymentDate;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private PaymentMethod paymentMethodType;

    @Column(nullable = false)
    private PaymentStatus paymentStatus;
}
