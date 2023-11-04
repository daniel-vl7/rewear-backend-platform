package com.acme.rewear.platform.store.interfaces.rest.resources;

public record CreateProductResource(String name, String description, String size, String state, String price, String color, String urlToImg) {
}
