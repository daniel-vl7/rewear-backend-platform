package com.acme.rewear.platform.users.interfaces.rest.resources;

public record RegisterUserResource(String firstName, String lastName, String username, String email , String password) {
}
