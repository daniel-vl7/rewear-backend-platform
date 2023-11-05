package com.acme.rewear.platform.users.interfaces.rest.transform;

import com.acme.rewear.platform.users.domain.model.commands.UpdateUserCommand;
import com.acme.rewear.platform.users.interfaces.rest.resources.UpdateUserResource;
import com.acme.rewear.platform.users.interfaces.rest.resources.UserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource) {
        return new UpdateUserCommand(
                resource.id(),
                resource.firstName(),
                resource.lastName(),
                resource.username(),
                resource.email(),
                resource.password()
        );
    }
}
