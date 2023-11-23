package com.acme.rewear.platform.store.domain.services;

import com.acme.rewear.platform.store.domain.model.aggregates.Product;
import com.acme.rewear.platform.store.domain.model.queries.GetAllProductsQuery;
import com.acme.rewear.platform.store.domain.model.queries.GetProductByIdQuery;
import com.acme.rewear.platform.store.domain.model.queries.GetProductByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    Optional<Product> handle(GetProductByNameQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(GetAllProductsQuery query);

}
