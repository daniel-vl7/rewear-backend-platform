package com.savewise.rewear.platform.store.interfaces.rest.transform;

import com.savewise.rewear.platform.store.domain.model.commands.UpdateEventCommand;
import com.savewise.rewear.platform.store.interfaces.rest.resources.UpdateEventResource;

public class UpdateEventCommandFromResourceAssembler {
    public static UpdateEventCommand toCommandFromResource(Long eventId,UpdateEventResource resource){
        return new UpdateEventCommand(
                eventId,
                resource.name(),
                resource.description(),
                resource.location(),
                resource.date(),
                resource.time()
        );
    }
}
