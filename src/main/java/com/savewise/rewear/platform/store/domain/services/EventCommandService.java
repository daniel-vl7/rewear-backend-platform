package com.savewise.rewear.platform.store.domain.services;

import com.savewise.rewear.platform.store.domain.model.aggregates.Event;
import com.savewise.rewear.platform.store.domain.model.commands.CreateEventCommand;
import com.savewise.rewear.platform.store.domain.model.commands.DeleteEventCommand;
import com.savewise.rewear.platform.store.domain.model.commands.UpdateEventCommand;

import java.util.Optional;

public interface EventCommandService {

    Optional<Event> handle(CreateEventCommand command);

    Optional<Event> handle(DeleteEventCommand command);

    Optional<Event> handle(UpdateEventCommand command);
}
