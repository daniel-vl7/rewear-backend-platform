package com.acme.rewear.platform.users.domain.model.commands;

public record LoginUserCommand(Long id, String username, String password) {
}
