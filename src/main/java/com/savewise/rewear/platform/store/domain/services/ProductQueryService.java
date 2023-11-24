package com.savewise.rewear.platform.store.domain.services;

import com.savewise.rewear.platform.store.domain.model.aggregates.Product;
import com.savewise.rewear.platform.store.domain.model.queries.GetAllProductsQuery;
import com.savewise.rewear.platform.store.domain.model.queries.GetProductByIdQuery;
import com.savewise.rewear.platform.store.domain.model.queries.GetProductByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    Optional<Product> handle(GetProductByNameQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(GetAllProductsQuery query);

}
