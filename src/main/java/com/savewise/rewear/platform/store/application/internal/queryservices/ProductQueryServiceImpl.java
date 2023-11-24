package com.savewise.rewear.platform.store.application.internal.queryservices;

import com.savewise.rewear.platform.store.domain.model.aggregates.Product;
import com.savewise.rewear.platform.store.domain.model.queries.GetAllProductsQuery;
import com.savewise.rewear.platform.store.domain.model.queries.GetProductByIdQuery;
import com.savewise.rewear.platform.store.domain.model.queries.GetProductByNameQuery;
import com.savewise.rewear.platform.store.domain.services.ProductQueryService;
import com.savewise.rewear.platform.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductByNameQuery query) {
        return productRepository.findByName(query.productName());
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.productId());
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }
}
