package com.savewise.rewear.platform.store.interfaces.rest.transform;

import com.savewise.rewear.platform.store.domain.model.aggregates.Event;
import com.savewise.rewear.platform.store.interfaces.rest.resources.EventResource;

public class EventResourceFromEntityAssembler {
    public static EventResource toResourceFromEntity(Event entity){
        return new EventResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getLocation(), entity.getDate(), entity.getTime());
    }
}
