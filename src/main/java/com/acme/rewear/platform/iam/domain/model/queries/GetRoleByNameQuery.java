package com.acme.rewear.platform.iam.domain.model.queries;

import com.acme.rewear.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
