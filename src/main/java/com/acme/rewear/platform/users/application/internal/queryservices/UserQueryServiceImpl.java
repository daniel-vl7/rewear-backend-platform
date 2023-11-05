package com.acme.rewear.platform.users.application.internal.queryservices;

import com.acme.rewear.platform.users.domain.model.aggregates.User;
import com.acme.rewear.platform.users.domain.model.queries.GetAllUsersQuery;
import com.acme.rewear.platform.users.domain.model.queries.GetUserByIdQuery;
import com.acme.rewear.platform.users.domain.services.UserQueryService;
import com.acme.rewear.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository _userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return _userRepository.findById(query.id());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return _userRepository.findAll();
    }
}

