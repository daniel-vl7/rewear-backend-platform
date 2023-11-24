package com.acme.rewear.platform.store.infrastructure.persistence.jpa.repositories;

import com.acme.rewear.platform.store.domain.model.aggregates.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
