package com.acme.rewear.platform.store.interfaces.rest.transform;

import com.acme.rewear.platform.store.domain.model.commands.CreateProductCommand;
import com.acme.rewear.platform.store.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource){
        return new CreateProductCommand(
                resource.name(),
                resource.description(),
                resource.size(),
                resource.state(),
                resource.price(),
                resource.color(),
                resource.urlToImg()
        );
    }

}
