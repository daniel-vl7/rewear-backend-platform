package com.savewise.rewear.platform.iam.domain.model.entities;

import com.savewise.rewear.platform.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

/**
 * Role entity
 *
 * <p>
 *     This entity represents the role of a user in the system.
 *     It is used to define the permissions of a user.
 * </p>
 */
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    /**
     * Constructor
     * @param name Role name
     */
    public Role(Roles name) {
        this.name = name;
    }

    /**
     * Get the role name as a String
     * @return Role name
     */
    public String getStringName() {
        return name.name();
    }

    /**
     * Get the default role as defined in the system
     * @return Role name
     */
    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_SHOPPER);
    }

    /**
     * Get the role from a String
     * @param name Role name
     * @return Role
     */
    public static Role toRoleFromName(String name) {
        try {
            return new Role(Roles.valueOf(name));
        } catch (IllegalArgumentException e) {
            // Handle the exception, log it, or return a default role.
            return getDefaultRole();
        }
    }

    /**
     * Validate the role set if it is null or empty
     * @param roles Role set
     * @return Role set
     */
    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;
    }
}