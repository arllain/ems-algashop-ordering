package com.arllain.algashop.ordering.domain.validator;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class FieldValidations {

    private FieldValidations() {
    }


    public static void requireNonBlank(String value) {
        requireNonBlank(value, "");
    }

    public static void requireNonBlank(String value, String message) {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void requiresValidEmail(String email) {
        requiresValidEmail(email, null);
    }

    public static void requiresValidEmail(String email, String errorMessage) {
        Objects.requireNonNull(email, errorMessage);

        if (email.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
