package com.savewise.rewear.platform.iam.interfaces.rest.transform;

import com.savewise.rewear.platform.iam.domain.model.commands.LogInCommand;
import com.savewise.rewear.platform.iam.interfaces.rest.resources.LogInResource;

public class LogInCommandFromResourceAssembler {
    public static LogInCommand toCommandFromResource(LogInResource signInResource) {
        return new LogInCommand(signInResource.username(), signInResource.password());
    }
}
