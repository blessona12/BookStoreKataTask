package com.kata.bookstore.model;

import java.util.List;

public record CartRequest(List<CartItemRequest> items) {}