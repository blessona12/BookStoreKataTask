package com.kata.bookstore.service;

import com.kata.bookstore.config.DiscountProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PricingService {

    private static final double BOOK_PRICE = 50.0;
    private final DiscountProperties properties;

    public PricingService(DiscountProperties properties) {
        this.properties = properties;
    }

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

        int[] currentCounts = counts.clone();
        List<Integer> groups = new ArrayList<>();

        while (true) {
            int distinct = 0;
            for (int c : currentCounts) {
                if (c > 0) distinct++;
            }

            if (distinct == 0) break;

            groups.add(distinct);

            for (int i = 0; i < currentCounts.length; i++) {
                if (currentCounts[i] > 0) {
                    currentCounts[i]--;
                }
            }
        }

        optimize(groups);

        double totalCost = 0.0;
        for (int size : groups) {
            double discount = properties.getDiscounts().getOrDefault(size, 0.0);
            totalCost += size * BOOK_PRICE * (1 - discount);
        }

        return totalCost;
    }

    private void optimize(List<Integer> groups) {
        while (groups.contains(5) && groups.contains(3)) {
            groups.remove(Integer.valueOf(5));
            groups.remove(Integer.valueOf(3));
            groups.add(4);
            groups.add(4);
        }
    }
}