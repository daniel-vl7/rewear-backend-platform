package com.savewise.rewear.platform.store.interfaces.rest;

import com.savewise.rewear.platform.iam.domain.model.commands.DeleteUserCommand;
import com.savewise.rewear.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.savewise.rewear.platform.iam.interfaces.rest.resources.*;
import com.savewise.rewear.platform.iam.interfaces.rest.transform.*;
import com.savewise.rewear.platform.store.domain.model.commands.DeleteEventCommand;
import com.savewise.rewear.platform.store.domain.model.queries.GetAllEventsQuery;
import com.savewise.rewear.platform.store.domain.model.queries.GetEventByIdQuery;
import com.savewise.rewear.platform.store.domain.services.EventCommandService;
import com.savewise.rewear.platform.store.domain.services.EventQueryService;
import com.savewise.rewear.platform.store.interfaces.rest.resources.CreateEventResource;
import com.savewise.rewear.platform.store.interfaces.rest.resources.EventResource;
import com.savewise.rewear.platform.store.interfaces.rest.resources.UpdateEventResource;
import com.savewise.rewear.platform.store.interfaces.rest.transform.CreateEventCommandFromResourceAssembler;
import com.savewise.rewear.platform.store.interfaces.rest.transform.EventResourceFromEntityAssembler;
import com.savewise.rewear.platform.store.interfaces.rest.transform.UpdateEventCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/events", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Events", description = "Events Management Endpoints")
public class EventsController {
    private final EventCommandService eventCommandService;
    private final EventQueryService eventQueryService;

    public EventsController(EventCommandService eventCommandService, EventQueryService eventQueryService) {
        this.eventCommandService = eventCommandService;
        this.eventQueryService = eventQueryService;
    }

    /**
     * Get all events
     * @return List of events resources if found, otherwise empty list
     */
    @GetMapping
    public ResponseEntity<List<EventResource>> getAllEvents() {
        var getAllEventQuery = new GetAllEventsQuery();
        var events = eventQueryService.handle(getAllEventQuery);
        var eventResources = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventResources);
    }

    /**
     * Get event by id
     * @param eventId User id
     * @return event resource if found, otherwise not found
     */
    @GetMapping(value = "/{eventId}")
    public ResponseEntity<EventResource> getEventById(@PathVariable("eventId") Long eventId) {
        var getEventByIdQuery = new GetEventByIdQuery(eventId);
        var event = eventQueryService.handle(getEventByIdQuery);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
        return ResponseEntity.ok(eventResource);
    }

    /**
     * Create event
     * @param resource The event resource
     * @return The event resource
     */
    @PostMapping
    public ResponseEntity<EventResource> createEvent(@RequestBody CreateEventResource resource) {
        var createEventCommand = CreateEventCommandFromResourceAssembler.toCommandFromResource(resource);
        var event = eventCommandService.handle(createEventCommand);
        if (event.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
        return new ResponseEntity<>(eventResource, HttpStatus.CREATED);
    }

    /**
     * Update event by id
     * @param eventId Event id
     * @return The event resource, otherwise not found
     */
    @PutMapping(value = "/{eventId}")
    public ResponseEntity<EventResource> updateEventById(@PathVariable Long eventId, @RequestBody UpdateEventResource resource) {
        var updateEventCommand = UpdateEventCommandFromResourceAssembler.toCommandFromResource(eventId, resource);
        eventCommandService.handle(updateEventCommand);

        var getEventByIdQuery = new GetEventByIdQuery(eventId);
        var event = eventQueryService.handle(getEventByIdQuery);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
        return new ResponseEntity<>(eventResource, HttpStatus.CREATED);
    }

    /**
     * Delete user by id
     * @param eventId User id
     * @return No content if deleted, otherwise not found
     */
    @DeleteMapping(value = "/{eventId}")
    public ResponseEntity<String> deleteEventById(@PathVariable Long eventId) {
        try {
            var deleteEventCommand = new DeleteEventCommand(eventId);
            eventCommandService.handle(deleteEventCommand);
            return ResponseEntity.ok("Event with id " + eventId + " deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting event with id " + eventId + ": " + ex.getMessage());
        }
    }
}
