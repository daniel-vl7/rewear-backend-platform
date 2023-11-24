package com.acme.rewear.platform.iam.interfaces.rest.resources;

import com.acme.rewear.platform.iam.domain.model.entities.Role;

import java.util.List;

public record UpdateResource(String username, String email, String password) {
}
