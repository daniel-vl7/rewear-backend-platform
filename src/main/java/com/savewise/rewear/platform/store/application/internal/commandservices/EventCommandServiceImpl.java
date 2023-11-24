package com.savewise.rewear.platform.store.application.internal.commandservices;

import com.savewise.rewear.platform.store.domain.model.aggregates.Event;
import com.savewise.rewear.platform.store.domain.model.commands.CreateEventCommand;
import com.savewise.rewear.platform.store.domain.model.commands.DeleteEventCommand;
import com.savewise.rewear.platform.store.domain.model.commands.UpdateEventCommand;
import com.savewise.rewear.platform.store.domain.services.EventCommandService;
import com.savewise.rewear.platform.store.infrastructure.persistence.jpa.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventCommandServiceImpl implements EventCommandService {
    private final EventRepository eventRepository;

    public EventCommandServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> handle(CreateEventCommand command) {
        var event = new Event(command.name(), command.description(), command.location() ,command.date(), command.time());
        eventRepository.findById(command.eventId())
                .orElseThrow(() -> new IllegalArgumentException("Event with id " + command.eventId() + "does not exist"));
        eventRepository.save(event);
        return eventRepository.findById(command.eventId());
    }

    @Override
    public Optional<Event> handle(UpdateEventCommand command) {
        var event = eventRepository.findById(command.eventId())
                .orElseThrow(() -> new IllegalArgumentException("Event with id " + command.eventId() + "does not exist"));
        event.setName(command.name());
        event.setDescription(command.description());
        event.setLocation(command.location());
        event.setDate(command.date());
        event.setTime(command.time());
        eventRepository.save(event);
        return eventRepository.findById(command.eventId());
    }

    @Override
    public Optional<Event> handle(DeleteEventCommand command) {
        var event = eventRepository.findById(command.eventId())
                .orElseThrow(() -> new IllegalArgumentException("Event with id " + command.eventId() + "does not exist"));
        eventRepository.delete(event);
        return Optional.of(event);
    }
}
