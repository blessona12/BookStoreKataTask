package com.kata.bookstore.service;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private static final double BOOK_PRICE = 50.0;
    private static final double[] DISCOUNT_RATES = {0.0, 0.0, 0.05, 0.10, 0.20, 0.25};

    public double calculate(int[] counts) {
        if (counts == null || counts.length != 5) {
            throw new IllegalArgumentException("Must provide counts for exactly 5 books");
        }

        int totalBooks = 0;
        int distinctBooks = 0;

        for (int count : counts) {
            totalBooks += count;
            if (count > 0) {
                distinctBooks++;
            }
        }

        if (totalBooks == 0) {
            return 0.0;
        }

        // If all books are distinct (single set), apply discount
        if (totalBooks == distinctBooks) {
            double discount = DISCOUNT_RATES[distinctBooks];
            return totalBooks * BOOK_PRICE * (1 - discount);
        }

        // No discount for duplicates yet
        return totalBooks * BOOK_PRICE;
    }
}