package com.acme.rewear.platform.store.domain.services;

import com.acme.rewear.platform.store.domain.model.aggregates.Product;
import com.acme.rewear.platform.store.domain.model.commands.CreateProductCommand;
import com.acme.rewear.platform.store.domain.model.commands.DeleteProductCommand;
import com.acme.rewear.platform.store.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);

    Optional<Product> handle(DeleteProductCommand command);

    Optional<Product> handle(UpdateProductCommand command);
}
