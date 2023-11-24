package com.savewise.rewear.platform.store.domain.model.commands;

public record UpdateProductCommand(Long productId, String name, String description, String size, String state, Double price, String color, String urlToImg) {
}
