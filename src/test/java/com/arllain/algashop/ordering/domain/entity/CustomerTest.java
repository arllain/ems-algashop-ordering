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

    @Test
    void given_unarchivedCustomer_whenArchice_shouldAnonymize() {
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
        customer.archive();

        Assertions.assertWith(customer,
                c -> Assertions.assertThat(c.fullName()).isEqualTo("Anonymous"),
                c -> Assertions.assertThat(c.email()).isNotEqualTo("john.doe@gmail.com"),
                c -> Assertions.assertThat(c.phone()).isEqualTo("000-000-0000"),
                c -> Assertions.assertThat(c.document()).isEqualTo("000-00-0000"),
                c -> Assertions.assertThat(c.birthDate()).isNull()
        );
    }
}