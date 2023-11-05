package com.acme.rewear.platform.users.interfaces.acl;

import com.acme.rewear.platform.users.domain.services.UserCommandService;
import com.acme.rewear.platform.users.domain.services.UserQueryService;

public class UserContextFacade {
    private final UserCommandService _userCommandService;
    private final UserQueryService _userQueryService;

    public UserContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        _userCommandService = userCommandService;
        _userQueryService = userQueryService;
    }


}
