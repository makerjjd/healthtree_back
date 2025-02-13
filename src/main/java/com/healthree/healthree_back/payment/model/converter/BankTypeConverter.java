package com.healthree.healthree_back.payment.model.converter;

import com.healthree.healthree_back.payment.model.type.BankType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BankTypeConverter implements AttributeConverter<BankType, String> {

    @Override
    public String convertToDatabaseColumn(BankType bankType) {
        if (bankType == null) {
            return null;
        }
        return bankType.getCode();
    }

    @Override
    public BankType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return BankType.of(code);
    }

}
