package com.savewise.rewear.platform.store.infrastructure.persistence.jpa.repositories;

import com.savewise.rewear.platform.iam.domain.model.aggregates.User;
import com.savewise.rewear.platform.store.domain.model.aggregates.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    /**
     * Find user by name
     * @param name the username to search
     * @return event object if found
     */
    Optional<Event> findByName(String name);

    /**
     * Check if event exists by name
     * @param name the username to search
     * @return true if event exists
     */
    Boolean existsByName(String name);
}
