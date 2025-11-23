package com.arllain.algashop.ordering.domain.entity;

import com.arllain.algashop.ordering.domain.utility.IdGenerator;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Test
    public void testingCustomer() {

        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
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