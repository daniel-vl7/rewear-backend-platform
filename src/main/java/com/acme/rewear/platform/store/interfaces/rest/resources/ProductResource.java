package com.acme.rewear.platform.store.interfaces.rest.resources;

public record ProductResource(Long id, String productName, String productDescription, String size, String state, Double price, String color, String urlToImg) {
}
