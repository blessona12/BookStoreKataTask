package com.kata.bookstore.model;

public record Invoice(
        double subtotal,
        double discount,
        double total
) {}