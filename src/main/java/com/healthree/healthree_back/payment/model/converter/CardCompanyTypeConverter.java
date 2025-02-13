package com.healthree.healthree_back.payment.model.converter;

import com.healthree.healthree_back.payment.model.entity.CardCompanyType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CardCompanyTypeConverter implements AttributeConverter<CardCompanyType, String> {

    @Override
    public String convertToDatabaseColumn(CardCompanyType cardCompanyType) {
        if (cardCompanyType == null) {
            return null;
        }
        return cardCompanyType.getCode();
    }

    @Override
    public CardCompanyType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return CardCompanyType.of(code);
    }

}
