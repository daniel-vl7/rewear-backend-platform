package com.acme.rewear.platform.users.domain.model.commands;

import java.util.Optional;

public record UpdateUserCommand(
        Long id,
        Optional<String> firstName,
        Optional<String> lastName,
        Optional<String> username,
        Optional<String> email,
        Optional<String> password
) { }
