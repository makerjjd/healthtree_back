package com.healthree.healthree_back.common.model.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeListConverter implements AttributeConverter<List<LocalDateTime>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<LocalDateTime> attribute) {
        String strings = "";

        if (attribute == null || attribute.isEmpty()) {
            return strings;
        }

        for (LocalDateTime strItem : attribute) {
            if (strings.length() > 0) {
                strings += SPLIT_CHAR;
            }

            strings += strItem.toString();
        }

        return strings;
    }

    @Override
    public List<LocalDateTime> convertToEntityAttribute(String dbData) {
        List<LocalDateTime> strings = new ArrayList<>();

        for (String strItem : dbData.split(SPLIT_CHAR)) {
            strings.add(LocalDateTime.parse(strItem));
        }

        return strings;
    }
}
