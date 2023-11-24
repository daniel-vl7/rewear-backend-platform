package com.savewise.rewear.platform.store.interfaces.rest.transform;

import com.savewise.rewear.platform.store.domain.model.commands.CreateProductCommand;
import com.savewise.rewear.platform.store.interfaces.rest.resources.CreateProductResource;

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
