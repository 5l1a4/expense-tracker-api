package com.analistas.expensetrackerapi.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum EnumCategory {
    GROCERIES, LEISURE, ELECTRONICS, UTILITIES, CLOTHING, HEALTH, OTHERS;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static EnumCategory forValue(String value) {

        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Invalid enum value. Valid values are: " + Arrays.toString(EnumCategory.values()));
        }

        String normalized = value.toUpperCase().trim();

        try {
            return EnumCategory.valueOf(normalized);
        }catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid enum value: " + normalized + ". Valid values are: " + Arrays.toString(EnumCategory.values()));
        }

    }

    @JsonValue
    public String getValue() {
        return name();
    }

}
