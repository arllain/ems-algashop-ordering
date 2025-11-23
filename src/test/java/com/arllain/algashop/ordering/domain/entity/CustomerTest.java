package com.arllain.algashop.ordering.domain.entity;

import com.arllain.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCustomer_shouldGenerateException() {

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Customer(
                    IdGenerator.generateTimeBasedUUID(),
                    "John Doe",
                    LocalDate.of(1990, 6, 11),
                    "invalid",
                    "478-256-2504",
                    "255-08-2504",
                    false,
                    OffsetDateTime.now()
            );
        });
    }


    @Test
    void given_invalidEmail_whenTryUpdateCustomerEmail_shouldGenerateException() {
        Customer customer = new Customer(
            IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(1990, 6, 11),
                "john.doe@gmail.com",
                "478-256-2504",
                "255-08-2504",
                false,
                OffsetDateTime.now()
            );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            customer.changeEmail("invalid");
        });
    }
}