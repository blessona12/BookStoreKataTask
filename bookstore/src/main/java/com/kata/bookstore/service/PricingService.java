package com.kata.bookstore.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PricingService {

    public double calculate(int[] counts) {
        if (counts == null || counts.length == 0) {
            return 0.0;
        }

        return 0.0;

        }
}
