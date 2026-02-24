package com.kata.bookstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PricingServiceTest {

    @Autowired
    private PricingService service;

    @Test
    void testEmptyCart() {
        assertEquals(0.0, service.calculate(new int[]{0}), 0.001);
    }
}
