package com.acme.rewear.platform.users.domain.model.commands;

public record UpdateUserCommand(Long id, String firstName, String lastName, String username, String email, String password) {
}
