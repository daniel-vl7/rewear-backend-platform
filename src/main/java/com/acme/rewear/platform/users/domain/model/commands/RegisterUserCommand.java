package com.acme.rewear.platform.users.domain.model.commands;

public record RegisterUserCommand(String firstName, String lastName, String username, String email, String password) {
}
