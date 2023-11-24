package com.savewise.rewear.platform.store.domain.services;

import com.savewise.rewear.platform.store.domain.model.aggregates.Product;
import com.savewise.rewear.platform.store.domain.model.commands.CreateProductCommand;
import com.savewise.rewear.platform.store.domain.model.commands.DeleteProductCommand;
import com.savewise.rewear.platform.store.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);

    Optional<Product> handle(DeleteProductCommand command);

    Optional<Product> handle(UpdateProductCommand command);
}
