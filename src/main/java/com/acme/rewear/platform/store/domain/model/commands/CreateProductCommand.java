package com.acme.rewear.platform.store.domain.model.commands;

public record CreateProductCommand(String name, String description, String size, String state, Double price, String color, String urlToImg) {

}
