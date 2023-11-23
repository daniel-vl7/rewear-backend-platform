package com.acme.rewear.platform.users.application.internal.commandservices;

import com.acme.rewear.platform.users.domain.model.aggregates.User;
import com.acme.rewear.platform.users.domain.model.commands.DeleteUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.LoginUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.RegisterUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.UpdateUserCommand;
import com.acme.rewear.platform.users.domain.services.UserCommandService;
import com.acme.rewear.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository _userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public Long handle(RegisterUserCommand command) {
        var newUser = new User(command.firstName(), command.lastName(), command.username(), command.email(), command.password());
        _userRepository.findUserByUsername(newUser.getUsername()).map(user -> {
            throw new IllegalArgumentException("Username " + command.username() + " already exists");
        });

        _userRepository.save(newUser);
        return newUser.getId();
    }

    @Override
    public boolean handle(LoginUserCommand command) {
        User user = _userRepository.findUserByUsername(command.username())
                .orElse(null);

        if (user == null || !user.getPassword().equals(command.password())) {
            return false;
        }

        return true;
    }
    @Override
    public void handle(UpdateUserCommand command) {
        User user = _userRepository.findUserById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + command.id() + " not found"));

        command.firstName().ifPresent(user::setFirstName);
        command.lastName().ifPresent(user::setLastName);
        command.username().ifPresent(user::setUsername);
        command.email().ifPresent(user::setEmail);
        command.password().ifPresent(user::setPassword);

        _userRepository.save(user);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        User user = _userRepository.findUserById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + command.id() + " not found"));

        _userRepository.delete(user);
    }
}
