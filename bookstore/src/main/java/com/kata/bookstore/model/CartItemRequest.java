package com.kata.bookstore.model;

import com.kata.bookstore.service.BookType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemRequest(
        @NotNull
        BookType book,
        @Min(1)
        int quantity
) {}
