package com.acme.rewear.platform.users.domain.commands;

public record UpdateUserCommand(Long id, String firstName, String lastName, String username, String email, String password) {
}
