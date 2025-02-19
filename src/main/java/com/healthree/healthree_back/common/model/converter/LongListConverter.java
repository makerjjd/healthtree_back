package com.healthree.healthree_back.common.model.converter;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LongListConverter implements AttributeConverter<List<Long>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        String strings = "";

        if (attribute == null || attribute.isEmpty()) {
            return strings;
        }

        for (Long longItem : attribute) {
            if (strings.length() > 0) {
                strings += SPLIT_CHAR;
            }

            strings += String.valueOf(longItem);

        }

        return strings;
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        List<Long> strings = new ArrayList<>();

        for (String strItem : dbData.split(SPLIT_CHAR)) {
            strings.add(Long.parseLong(strItem));
        }

        return strings;
    }
}
