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
        assertEquals(0.0, service.calculate(new int[]{0, 0, 0, 0, 0}), 0.001);
    }

    @Test
    void testSingleBook() {
        assertEquals(50.0, service.calculate(new int[]{1, 0, 0, 0, 0}), 0.001);
    }

    @Test
    void testTwoBooks() {
        assertEquals(100.0, service.calculate(new int[]{2, 0, 0, 0, 0}), 0.001);
    }

    @Test
    void testTwoDifferentBooks() {
        assertEquals(95.0, service.calculate(new int[]{1, 1, 0, 0, 0}), 0.001);
    }
    @Test
    void testThreeDifferentBooks() {
        assertEquals(135.0, service.calculate(new int[]{1, 1, 1, 0, 0}), 0.001);
    }
}
