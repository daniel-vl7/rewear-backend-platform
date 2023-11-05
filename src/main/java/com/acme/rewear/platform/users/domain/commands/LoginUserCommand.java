package com.acme.rewear.platform.users.domain.commands;

public record LoginUserCommand(Long id, String username, String password) {
}
