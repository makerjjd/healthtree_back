package com.healthree.healthree_back.payment.model.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.healthree.healthree_back.payment.model.converter.CardCompanyTypeConverter;
import com.healthree.healthree_back.payment.model.converter.CardOwnerTypeConverter;
import com.healthree.healthree_back.payment.model.converter.CardTypeConverter;
import com.healthree.healthree_back.payment.model.type.CardOwnerType;
import com.healthree.healthree_back.payment.model.type.CardType;

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
@Table(name = "card_detail")
public class CardDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long paymentId;

    @Column
    @Convert(converter = CardCompanyTypeConverter.class)
    private CardCompanyType issuerCode;

    @Column
    @Convert(converter = CardCompanyTypeConverter.class)
    private CardCompanyType acquirerCode;

    @Column
    private String number;

    @Column
    @Convert(converter = CardTypeConverter.class)
    private CardType cardType;

    @Column
    @Convert(converter = CardOwnerTypeConverter.class)
    private CardOwnerType ownerType;
}
