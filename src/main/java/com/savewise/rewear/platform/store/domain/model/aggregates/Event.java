package com.savewise.rewear.platform.store.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Event extends AbstractAggregateRoot<Event> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private String name;
    private String description;
    private String location;
    private String date;
    private String time;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Event() {
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.location = Strings.EMPTY;
        this.date = Strings.EMPTY;
        this.time = Strings.EMPTY;
    }

    public Event(String name, String description, String location, String date, String time) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
    }
}
