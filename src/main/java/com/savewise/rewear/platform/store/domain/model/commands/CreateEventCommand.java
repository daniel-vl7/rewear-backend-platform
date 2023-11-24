package com.savewise.rewear.platform.store.domain.model.commands;

public record CreateEventCommand(String name, String description, String location, String date, String time) {
}
