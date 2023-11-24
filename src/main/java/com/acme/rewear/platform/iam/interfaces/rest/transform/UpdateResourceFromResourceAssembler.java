package com.acme.rewear.platform.iam.interfaces.rest.transform;

import com.acme.rewear.platform.iam.domain.model.commands.UpdateUserCommand;
import com.acme.rewear.platform.iam.interfaces.rest.resources.UpdateResource;

public class UpdateResourceFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateResource resource) {
        return new UpdateUserCommand(userId, resource.username(), resource.email(), resource.password());
    }
}
