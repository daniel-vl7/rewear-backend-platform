package com.acme.rewear.platform.users.interfaces.rest.transform;

import com.acme.rewear.platform.users.domain.model.commands.RegisterUserCommand;
import com.acme.rewear.platform.users.interfaces.rest.resources.RegisterUserResource;
import com.acme.rewear.platform.users.interfaces.rest.resources.UserResource;

public class RegisterUserCommandFromResourceAssembler {
    public static RegisterUserCommand toCommandFromResource(RegisterUserResource resource) {
        return new RegisterUserCommand(
                resource.firstName(),
                resource.lastName(),
                resource.username(),
                resource.email(),
                resource.password()
        );
    }
}
