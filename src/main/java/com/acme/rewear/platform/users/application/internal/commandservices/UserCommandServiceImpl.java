package com.acme.rewear.platform.users.application.internal.commandservices;

import com.acme.rewear.platform.users.domain.model.aggregates.User;
import com.acme.rewear.platform.users.domain.model.commands.DeleteUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.LoginUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.RegisterUserCommand;
import com.acme.rewear.platform.users.domain.model.commands.UpdateUserCommand;
import com.acme.rewear.platform.users.domain.services.UserCommandService;
import com.acme.rewear.platform.users.interfaces.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository _userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public void handle(RegisterUserCommand command) {
        User newUser = new User(command.firstName(), command.lastName(), command.username(), command.email(), command.password());

        _userRepository.save(newUser);
    }

    @Override
    public User handle(LoginUserCommand command) {
        return _userRepository.findUserById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + command.id() + " not found"));
    }

    @Override
    public void handle(UpdateUserCommand command) {
        User user = _userRepository.findUserById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + command.id() + " not found"));

        user.updateFirstName(command.firstName());
        user.updateLastName(command.lastName());
        user.updateUsername(command.username());
        user.updateEmail(command.email());
        user.updatePassword(command.password());

        _userRepository.save(user);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        User user = _userRepository.findUserById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + command.id() + " not found"));

        _userRepository.delete(user);
    }
}
