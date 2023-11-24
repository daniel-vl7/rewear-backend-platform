package com.savewise.rewear.platform.store.interfaces.rest.transform;

import com.savewise.rewear.platform.store.domain.model.commands.UpdateProductCommand;
import com.savewise.rewear.platform.store.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFomResourceAssembles {
    public static UpdateProductCommand toCommandFromResource(Long productId, UpdateProductResource resource) {
        return new UpdateProductCommand(productId, resource.name(), resource.description(), resource.size(), resource.state(), resource.price(), resource.color(), resource.urlToImg());
    }
}
