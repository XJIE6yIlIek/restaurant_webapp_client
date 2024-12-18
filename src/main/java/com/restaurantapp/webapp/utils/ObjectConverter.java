package com.restaurantapp.webapp.utils;

import javafx.util.StringConverter;

import java.util.Objects;

public class ObjectConverter {

    public class LongStringConverter extends StringConverter<Long> {

        @Override
        public String toString(Long value) {
            return (!Objects.isNull(value)) ? value.toString() : "";
        }

        @Override
        public Long fromString(String value) {
            if (Objects.isNull(value) || value.trim().isEmpty()) {
                return null;
            }
            return Long.valueOf(value.trim());
        }
    }

    public class FloatStringConverter extends StringConverter<Float> {

        @Override
        public String toString(Float value) {
            return (!Objects.isNull(value)) ? value.toString() : "";
        }

        @Override
        public Float fromString(String value) {
            if (Objects.isNull(value) || value.trim().isEmpty()) {
                return null;
            }
            return Float.valueOf(value.trim());
        }
    }

}
