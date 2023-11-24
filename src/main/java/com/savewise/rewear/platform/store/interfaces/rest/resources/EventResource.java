package com.savewise.rewear.platform.store.interfaces.rest.resources;

public record EventResource(Long eventId, String name, String description, String location, String date, String time) {
}
