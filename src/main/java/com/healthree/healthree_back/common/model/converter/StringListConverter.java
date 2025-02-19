package com.healthree.healthree_back.common.model.converter;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        String strings = "";

        if (attribute == null || attribute.isEmpty()) {
            return strings;
        }

        for (String strItem : attribute) {
            if (strings.length() > 0) {
                strings += SPLIT_CHAR;
            }

            strings += strItem;

        }

        return strings;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        List<String> strings = new ArrayList<>();

        for (String strItem : dbData.split(SPLIT_CHAR)) {
            strings.add(strItem);
        }

        return strings;
    }
}
