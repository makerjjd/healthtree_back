package com.healthree.healthree_back.payment.model.converter;

import com.healthree.healthree_back.payment.model.type.CardType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CardTypeConverter implements AttributeConverter<CardType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CardType cartType) {
        if (cartType == null) {
            return null;
        }
        return cartType.getCode();
    }

    @Override
    public CardType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return CardType.of(code);
    }
}
