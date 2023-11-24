package com.savewise.rewear.platform.store.interfaces.rest;

import com.savewise.rewear.platform.store.domain.services.EventCommandService;
import com.savewise.rewear.platform.store.domain.services.EventQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
