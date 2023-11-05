package com.acme.rewear.platform.users.interfaces.rest.transform;

import com.acme.rewear.platform.users.domain.model.commands.LoginUserCommand;
import com.acme.rewear.platform.users.interfaces.rest.resources.LoginUserResource;

public class LoginUserCommandFromResourceAssembler {
    public static LoginUserCommand toCommandFromResource(LoginUserResource resource) {
        return new LoginUserCommand(
                resource.username(),
                resource.password()
        );
    }
}
