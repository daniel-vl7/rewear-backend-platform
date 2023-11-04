package com.acme.rewear.platform.store.application.internal.commandservices;

import com.acme.rewear.platform.store.domain.model.aggregates.Product;
import com.acme.rewear.platform.store.domain.model.commands.CreateProductCommand;
import com.acme.rewear.platform.store.domain.model.valueobjects.ProductName;
import com.acme.rewear.platform.store.domain.services.ProductCommandService;
import com.acme.rewear.platform.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long handle(CreateProductCommand command) {
        var productName = new ProductName(command.name());
        productRepository.findByName(productName).map(product -> {
            throw new IllegalArgumentException("Product with name" + command.name() + "already exists");
        });
        var product = new Product(command.name(), command.description(), command.size(), command.state(), command.price(), command.color(), command.urlToImg());
        productRepository.save(product);
        return product.getId();
    }
}
