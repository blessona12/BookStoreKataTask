package com.kata.bookstore.service;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private static final double BOOK_PRICE = 50.0;

    public double calculate(int[] counts) {
        if (counts == null || counts.length != 5) {
            throw new IllegalArgumentException("Must provide counts for exactly 5 books");
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

        int distinct = countDistinct(counts);

        if (totalBooks == distinct) {
            double discount = 0.0;
            if (totalBooks == 2) discount = 0.05;
            return totalBooks * BOOK_PRICE * (1 - discount);
        }

        return totalBooks * BOOK_PRICE;
    }

    private int countDistinct(int[] counts) {
        int distinct = 0;
        for (int count : counts) {
            if (count > 0) {
                distinct++;
            }
        }
        return distinct;
    }
}