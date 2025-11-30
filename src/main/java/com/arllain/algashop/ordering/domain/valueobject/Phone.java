package com.arllain.algashop.ordering.domain.valueobject;

import com.arllain.algashop.ordering.domain.validator.FieldValidations;

import java.util.Objects;

import static com.arllain.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;

public record Phone(String value) {

    public Phone(String value) {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
