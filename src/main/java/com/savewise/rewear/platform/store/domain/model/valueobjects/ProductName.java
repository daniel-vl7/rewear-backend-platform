package com.savewise.rewear.platform.store.domain.model.valueobjects;

public record ProductName(String name) {
    public ProductName(){
        this(null);
    }

    public ProductName{
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
    }

    public String getProductName() {
        return name;
    }
}
