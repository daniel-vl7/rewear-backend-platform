package com.savewise.rewear.platform.iam.domain.model.commands;

import com.savewise.rewear.platform.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String email, String password, List<Role> roles) {
}
