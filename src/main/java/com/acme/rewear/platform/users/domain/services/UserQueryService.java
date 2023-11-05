package com.acme.rewear.platform.users.domain.services;

import com.acme.rewear.platform.users.domain.model.aggregates.User;
import com.acme.rewear.platform.users.domain.model.queries.GetAllUsersQuery;
import com.acme.rewear.platform.users.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle (GetUserByIdQuery query);
    List<User> handle (GetAllUsersQuery query);
}
