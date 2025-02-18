package com.healthree.healthree_back.order.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Table(name = "order_item")
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class OrderItemEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer price;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
