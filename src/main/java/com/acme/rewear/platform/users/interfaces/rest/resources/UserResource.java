package com.acme.rewear.platform.users.interfaces.rest.resources;

public record UserResource(Long id, String firstName, String lastName, String username, String email ,String password) {
}
