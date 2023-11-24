package com.savewise.rewear.platform.store.interfaces.rest.transform;

import com.savewise.rewear.platform.store.domain.model.commands.CreateEventCommand;
import com.savewise.rewear.platform.store.interfaces.rest.resources.CreateEventResource;

public class CreateEventCommandFromResourceAssembler {
    public static CreateEventCommand toCommandFromResource(CreateEventResource resource){
        return new CreateEventCommand(
                resource.name(),
                resource.description(),
                resource.location(),
                resource.date(),
                resource.time()
        );
    }
}
