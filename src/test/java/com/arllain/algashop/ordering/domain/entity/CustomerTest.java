package com.arllain.algashop.ordering.domain.entity;

import org.junit.jupiter.api.Test;
import java.util.UUID;

class CustomerTest {

    @Test
    public void testingCustomer() {

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFullName("Arllain Silva");
        customer.setDocument("123456");
        customer.setLoyaltyPoints(10);
    }
}