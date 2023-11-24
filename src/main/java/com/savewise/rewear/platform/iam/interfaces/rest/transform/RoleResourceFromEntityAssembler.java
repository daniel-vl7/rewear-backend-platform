package com.savewise.rewear.platform.iam.interfaces.rest.transform;

import com.savewise.rewear.platform.iam.domain.model.entities.Role;
import com.savewise.rewear.platform.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
