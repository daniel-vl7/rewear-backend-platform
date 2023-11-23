package com.acme.rewear.platform.users.interfaces.rest.transform;

import com.acme.rewear.platform.users.domain.model.aggregates.User;
import com.acme.rewear.platform.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}
