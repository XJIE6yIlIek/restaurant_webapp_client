package com.restaurantapp.webapp.utils;

import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.LongStringConverter;

public class CustomObjectConverter {
    public static class CustomFloatStringConverter extends FloatStringConverter {
        private final FloatStringConverter converter = new FloatStringConverter();

        @Override
        public String toString(Float object) {
            try {
                return converter.toString(object);
            } catch (NumberFormatException e) {
                AlertUtils.showErrorMessage("Invalid input data type. Numeric allowed only in this cell.");
            }
            return null;
        }

        @Override
        public Float fromString(String string) {
            try {
                return converter.fromString(string);
            } catch (NumberFormatException e) {
                AlertUtils.showErrorMessage("Invalid input data type. Numeric allowed only in this cell.");
            }
            return null;
        }
    }

    public static class CustomLongStringConverter extends LongStringConverter {
        private final LongStringConverter converter = new LongStringConverter();

        @Override
        public String toString(Long object) {
            try {
                return converter.toString(object);
            } catch (NumberFormatException e) {
                AlertUtils.showErrorMessage("Invalid input data type. Numeric allowed only in this cell.");
            }
            return null;
        }

        @Override
        public Long fromString(String string) {
            try {
                return converter.fromString(string);
            } catch (NumberFormatException e) {
                AlertUtils.showErrorMessage("Invalid input data type. Numeric allowed only in this cell.");
            }
            return null;
        }
    }

}
