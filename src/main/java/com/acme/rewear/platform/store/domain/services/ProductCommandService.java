package com.acme.rewear.platform.store.domain.services;

import com.acme.rewear.platform.store.domain.model.commands.CreateProductCommand;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);
}
