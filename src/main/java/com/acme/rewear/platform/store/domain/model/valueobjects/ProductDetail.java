package com.acme.rewear.platform.store.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductDetail(
        String size,
        String state,
        String price,
        String color,
        String urlToImg
) {
    public ProductDetail() {
        this(null, null, null, null, null);
    }

    public ProductDetail {
        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("Size must not be null or blank");
        }

        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("Sate must not be null or blank");
        }

        if (price == null) {
            throw new IllegalArgumentException("Price must not be null");
        }

        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Color must not be null or blank");
        }
        if (urlToImg==null|| urlToImg.isBlank()){
            throw new IllegalArgumentException("urlToImg must not be null or blank");
        }
    }
    public ProductDetail(String size, String state, String color, String urlToImg){
        this(size, state, null, color, urlToImg);
    }

    public String getProductDetail() {
        return String.format("%s %s %s %s %s", size, state, price, color, urlToImg);
    }

}
