package com.acme.rewear.platform.users.interfaces.rest.transform;

import com.acme.rewear.platform.users.domain.model.commands.UpdateUserCommand;
import com.acme.rewear.platform.users.interfaces.rest.resources.UpdateUserResource;
import com.acme.rewear.platform.users.interfaces.rest.resources.UserResource;

import java.util.Optional;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(
                id,
                Optional.ofNullable(resource.firstName()),
                Optional.ofNullable(resource.lastName()),
                Optional.ofNullable(resource.username()),
                Optional.ofNullable(resource.email()),
                Optional.ofNullable(resource.password())
        );
    }
}
