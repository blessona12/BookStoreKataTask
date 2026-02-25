package com.kata.bookstore.controller;

import com.kata.bookstore.model.CartItemRequest;
import com.kata.bookstore.model.CartRequest;
import com.kata.bookstore.model.Invoice;
import com.kata.bookstore.service.PricingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final PricingService pricingService;

    public CheckoutController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping
    public Invoice checkout(@Valid @RequestBody CartRequest request) {
        int[] counts = convertToCountsArray(request);

        double subtotal = calculateSubtotal(counts);
        double total = pricingService.calculate(counts);
        double discountAmount = subtotal - total;

        return new Invoice(subtotal, discountAmount, total);
    }

    private int[] convertToCountsArray(CartRequest request) {
        int[] counts = new int[5];

        for (CartItemRequest item : request.items()) {
            int index = switch (item.book()) {
                case CLEAN_CODE -> 0;
                case CLEAN_CODER -> 1;
                case CLEAN_ARCHITECTURE -> 2;
                case TDD -> 3;
                case LEGACY_CODE -> 4;
            };
            counts[index] += item.quantity();
        }
        return counts;
    }

    private double calculateSubtotal(int[] counts) {
        double subtotal = 0.0;
        for (int qty : counts) {
            subtotal += qty * 50.0;   // TODO: can be moved to constant later
        }
        return subtotal;
    }
}