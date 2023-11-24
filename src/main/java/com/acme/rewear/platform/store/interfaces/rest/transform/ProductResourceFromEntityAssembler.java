package com.acme.rewear.platform.store.interfaces.rest.transform;

import com.acme.rewear.platform.store.domain.model.aggregates.Product;
import com.acme.rewear.platform.store.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity){
        return new ProductResource(entity.getId(), entity.getProductName(), entity.getProductDescription(), entity.getSize(), entity.getState(), entity.getPrice(), entity.getColor(), entity.getUrlToImg());
    }
}
