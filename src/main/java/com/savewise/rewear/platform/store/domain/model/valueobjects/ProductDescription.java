package com.savewise.rewear.platform.store.domain.model.valueobjects;

public record ProductDescription(String description) {
    public ProductDescription(){
        this(null);
    }

    public ProductDescription{
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
    }

    public String getProductDescription() {
        return description;
    }
}
