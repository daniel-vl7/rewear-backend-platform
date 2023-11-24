package com.acme.rewear.platform.store.domain.services;

import com.acme.rewear.platform.store.domain.model.aggregates.Event;
import com.acme.rewear.platform.store.domain.model.queries.GetAllEventsQuery;
import com.acme.rewear.platform.store.domain.model.queries.GetEventByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EventQueryService {

    Optional<Event> handle(GetEventByIdQuery query);

    List<Event> handle(GetAllEventsQuery query);
}
