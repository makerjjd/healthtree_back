package com.healthree.healthree_back.payment.model.converter;

import com.healthree.healthree_back.payment.model.type.CardOwnerType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CardOwnerTypeConverter implements AttributeConverter<CardOwnerType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CardOwnerType cardOwnerType) {
        if (cardOwnerType == null) {
            return null;
        }
        return cardOwnerType.getCode();
    }

    @Override
    public CardOwnerType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return CardOwnerType.of(code);
    }
}
