package com.savewise.rewear.platform.iam.domain.model.commands;

public record UpdateUserCommand(Long userId, String username, String email, String password) {
}
