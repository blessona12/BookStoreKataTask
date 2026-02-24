package com.kata.bookstore.service;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private static final double BOOK_PRICE = 50.0;

    public double calculate(int[] counts) {
        if (counts == null || counts.length == 0) {
            return 0.0;
        }

        int totalBooks = 0;
        for (int count : counts) {
            totalBooks += count;
        }

        if (totalBooks == 0) {
            return 0.0;
        }

        if (totalBooks == 1) {
            return BOOK_PRICE;
        }

        return totalBooks * BOOK_PRICE;
    }
}