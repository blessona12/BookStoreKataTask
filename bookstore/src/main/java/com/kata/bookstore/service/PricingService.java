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

        int totalBooks = sum(counts);
        if (totalBooks == 0) {
            return 0.0;
        }

        int[] currentCounts = counts.clone();
        List<Integer> groups = new ArrayList<>();

        while (true) {
            int distinct = countDistinct(currentCounts);
            if (distinct == 0) break;

            groups.add(distinct);
            decrementOneFromEach(currentCounts);
        }

        optimize(groups);

        return calculateTotalCost(groups);
    }

    private int sum(int[] counts) {
        int total = 0;
        for (int c : counts) total += c;
        return total;
    }

    private int countDistinct(int[] counts) {
        int count = 0;
        for (int c : counts) if (c > 0) count++;
        return count;
    }

    private void decrementOneFromEach(int[] counts) {
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) counts[i]--;
        }
    }

    private void optimize(List<Integer> groups) {
        // Replace 5 + 3 with 4 + 4 (better discount: 320 instead of 322.5)
        while (groups.contains(5) && groups.contains(3)) {
            groups.remove(Integer.valueOf(5));
            groups.remove(Integer.valueOf(3));
            groups.add(4);
            groups.add(4);
        }
    }

    private double calculateTotalCost(List<Integer> groups) {
        double totalCost = 0.0;
        for (int size : groups) {
            double discount = properties.getDiscounts().getOrDefault(size, 0.0);
            totalCost += size * BOOK_PRICE * (1 - discount);
        }
        return totalCost;
    }
}