package com.arllain.algashop.ordering.domain.entity;

import com.arllain.algashop.ordering.domain.valueobject.id.CustomerId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void shouldGenerate() {
        Order order = Order.draft(new CustomerId());
    }
}