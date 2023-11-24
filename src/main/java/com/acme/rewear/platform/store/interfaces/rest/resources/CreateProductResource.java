package com.acme.rewear.platform.store.interfaces.rest.resources;

public record CreateProductResource(String name, String description, String size, String state, Double price, String color, String urlToImg) {
}
