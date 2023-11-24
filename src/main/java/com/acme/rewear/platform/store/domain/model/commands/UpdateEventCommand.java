package com.acme.rewear.platform.store.domain.model.commands;

public record UpdateEventCommand(String name, String description, String location, String date, String time) {
}
