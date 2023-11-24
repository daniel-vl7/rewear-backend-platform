package com.acme.rewear.platform.store.interfaces.rest.resources;

public record UpdateProductResource(String name, String description, String size, String state, Double price, String color, String urlToImg) {
}
