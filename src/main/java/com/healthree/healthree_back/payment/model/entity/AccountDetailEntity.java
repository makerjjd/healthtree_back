package com.healthree.healthree_back.payment.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import com.healthree.healthree_back.payment.model.converter.BankTypeConverter;
import com.healthree.healthree_back.payment.model.type.BankType;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "account_detail")
public class AccountDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long paymentId;

    @Column
    private String account;

    @Column
    @Convert(converter = BankTypeConverter.class)
    private BankType bank;

    @Column
    private String name;

    @Column
    private LocalDateTime dueDate;
}
