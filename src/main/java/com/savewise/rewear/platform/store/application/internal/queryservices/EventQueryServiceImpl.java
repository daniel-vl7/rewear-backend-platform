package com.savewise.rewear.platform.store.application.internal.queryservices;

import com.savewise.rewear.platform.store.domain.model.aggregates.Event;
import com.savewise.rewear.platform.store.domain.model.queries.GetAllEventsQuery;
import com.savewise.rewear.platform.store.domain.model.queries.GetEventByIdQuery;
import com.savewise.rewear.platform.store.domain.services.EventQueryService;
import com.savewise.rewear.platform.store.infrastructure.persistence.jpa.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventQueryServiceImpl implements EventQueryService {
    private final EventRepository eventRepository;

    public EventQueryServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> handle(GetEventByIdQuery query) {
        return eventRepository.findById(query.eventId());
    }

    @Override
    public List<Event> handle(GetAllEventsQuery query) {
        return eventRepository.findAll();
    }
}
