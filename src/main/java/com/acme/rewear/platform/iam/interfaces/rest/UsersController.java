package com.acme.rewear.platform.iam.interfaces.rest;

import com.acme.rewear.platform.iam.domain.model.aggregates.User;
import com.acme.rewear.platform.iam.domain.model.commands.DeleteUserCommand;
import com.acme.rewear.platform.iam.domain.model.commands.UpdateUserCommand;
import com.acme.rewear.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.rewear.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.rewear.platform.iam.domain.services.UserCommandService;
import com.acme.rewear.platform.iam.domain.services.UserQueryService;
import com.acme.rewear.platform.iam.interfaces.rest.resources.UpdateResource;
import com.acme.rewear.platform.iam.interfaces.rest.resources.UserResource;
import com.acme.rewear.platform.iam.interfaces.rest.transform.UpdateResourceFromResourceAssembler;
import com.acme.rewear.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Users Controller
 * <p>
 *     This controller exposes the endpoints for the user management.
 *     It is responsible for handling the requests and responses for the user management.
 *      Includes the following endpoints:
 *     <ul>
 *         <li>GET /api/v1/users</li>
 *         <li>GET /api/v1/users/{userId}</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;


    public UsersController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    /**
     * Get all users
     * @return List of users resources if found, otherwise empty list
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUserQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUserQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * Get user by id
     * @param userId User id
     * @return User resource if found, otherwise not found
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    /**
     * Delete user by id
     * @param userId User id
     * @return No content if deleted, otherwise not found
     */
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        try {
            var deleteUserCommand = new DeleteUserCommand(userId);
            userCommandService.handle(deleteUserCommand);
            return ResponseEntity.ok("User with id " + userId + " deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user with id " + userId + ": " + ex.getMessage());
        }
    }

    /**
     * Update user by id
     * @param userId User id
     * @return No content if updated, otherwise not found
     */
    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResource> updateUserById(@PathVariable Long userId, @RequestBody UpdateResource resource) {
        var updateUserCommand = UpdateResourceFromResourceAssembler.toCommandFromResource(userId, resource);
        userCommandService.handle(updateUserCommand);

        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
}
