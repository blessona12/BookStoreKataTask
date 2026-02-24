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
        for (int count : counts) {
            totalBooks += count;
        }

        if (totalBooks == 0) {
            return 0.0;
        }

        // Make a working copy so we don't modify original array
        int[] currentCounts = counts.clone();
        double totalCost = 0.0;

        while (true) {
            // Count how many different books still have copies left
            int distinct = 0;
            for (int c : currentCounts) {
                if (c > 0) {
                    distinct++;
                }
            }

            if (distinct == 0) {
                break;   // no more books
            }

            // Apply discount for this group
            double discount = DISCOUNT_RATES[distinct];
            totalCost += distinct * BOOK_PRICE * (1 - discount);

            // Remove one copy from each book that still has some
            for (int i = 0; i < currentCounts.length; i++) {
                if (currentCounts[i] > 0) {
                    currentCounts[i]--;
                }
            }
        }

        return totalCost;
    }
}