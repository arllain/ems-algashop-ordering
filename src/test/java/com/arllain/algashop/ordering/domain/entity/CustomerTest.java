package com.arllain.algashop.ordering.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

class CustomerTest {

    @Test
    public void testingCustomer() {

        Customer customer = new Customer(
                UUID.randomUUID(),
                "Arllain Silva",
                LocalDate.of(1976,12,18),
                "email@email.com",
                "123487",
                "123456789",
                true,
                OffsetDateTime.now()
        );

        customer.addLoyaltyPoints(10);
    }
}