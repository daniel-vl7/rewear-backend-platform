package com.acme.rewear.platform.users.interfaces.persistence.jpa.repositories;

import com.acme.rewear.platform.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);
    List<User> findAllUsers();
}
