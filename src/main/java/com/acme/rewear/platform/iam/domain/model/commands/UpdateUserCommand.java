package com.acme.rewear.platform.iam.domain.model.commands;

import com.acme.rewear.platform.iam.domain.model.entities.Role;

import java.util.List;

public record UpdateUserCommand(Long userId, String username, String email, String password) {
}
