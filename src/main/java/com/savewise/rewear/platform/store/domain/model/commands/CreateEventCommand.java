package com.savewise.rewear.platform.store.domain.model.commands;

public record CreateEventCommand(Long eventId, String name, String description, String location, String date, String time) {
}
