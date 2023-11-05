package com.acme.rewear.platform.users.domain.services;

import com.acme.rewear.platform.users.domain.model.aggregates.User;
import com.acme.rewear.platform.users.domain.model.commands.DeleteUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.LoginUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.RegisterUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.UpdateUserCommand;

public interface UserCommandService {
    Long handle (RegisterUserCommand command);
    boolean handle (LoginUserCommand command);
    void handle (UpdateUserCommand command);
    void handle (DeleteUserCommand command);
}
