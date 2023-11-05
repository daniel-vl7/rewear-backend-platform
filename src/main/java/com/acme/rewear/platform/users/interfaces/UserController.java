package com.acme.rewear.platform.users.interfaces;

import com.acme.rewear.platform.users.domain.model.queries.GetAllUsersQuery;
import com.acme.rewear.platform.users.domain.model.queries.GetUserByIdQuery;
import com.acme.rewear.platform.users.domain.services.UserCommandService;
import com.acme.rewear.platform.users.domain.services.UserQueryService;
import com.acme.rewear.platform.users.interfaces.rest.resources.LoginUserResource;
import com.acme.rewear.platform.users.interfaces.rest.resources.RegisterUserResource;
import com.acme.rewear.platform.users.interfaces.rest.resources.UserResource;
import com.acme.rewear.platform.users.interfaces.rest.transform.LoginUserCommandFromResourceAssembler;
import com.acme.rewear.platform.users.interfaces.rest.transform.RegisterUserCommandFromResourceAssembler;
import com.acme.rewear.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserCommandService _userCommandService;
    private final UserQueryService _userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        _userCommandService = userCommandService;
        _userQueryService = userQueryService;
    }

    // Register User
    @PostMapping
    public ResponseEntity<UserResource> resisterUser(@RequestBody RegisterUserResource resource) {
        var registerUserCommand = RegisterUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var userId = _userCommandService.handle(registerUserCommand);
        if (userId == 0L)
        {
            return ResponseEntity.badRequest().build();
        }

        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = _userQueryService.handle(getUserByIdQuery);

        if (user.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    // Get User By Id
    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = _userQueryService.handle(getUserByIdQuery);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = _userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginUserResource resource) {
        var loginUserCommand = LoginUserCommandFromResourceAssembler.toCommandFromResource(resource);
        boolean loginSuccessful = _userCommandService.handle(loginUserCommand);

        return ResponseEntity.ok(loginSuccessful);
    }
}
