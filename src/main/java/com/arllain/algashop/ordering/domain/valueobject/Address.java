package com.arllain.algashop.ordering.domain.valueobject;

import com.arllain.algashop.ordering.domain.validator.FieldValidations;
import lombok.Builder;

import java.util.Objects;

@Builder
public record Address (
    String street,
    String complement,
    String number,
    String neighborhood,
    String city,
    String state,
    ZipCode zipCode
) {

    @Builder(toBuilder = true)
    public Address {
        FieldValidations.requiresNonBlank(street);
        FieldValidations.requiresNonBlank(neighborhood);
        FieldValidations.requiresNonBlank(city);
        FieldValidations.requiresNonBlank(number);
        FieldValidations.requiresNonBlank(state);
        Objects.requireNonNull(zipCode);
    }
}
