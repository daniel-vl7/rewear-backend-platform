package com.acme.rewear.platform.users.domain.commands;

public record RegisterUserCommand(String firstName, String lastName, String username, String email, String password) {
}
