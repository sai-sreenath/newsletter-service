package com.example.newsletterservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="subscribers")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String email;

    private boolean active = true;

    private Instant createdAt = Instant.now();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="subscriber_topic",
            joinColumns=@JoinColumn(name="subscriber_id"),
            inverseJoinColumns=@JoinColumn(name="topic_id"))
    private Set<Topic> topics = new HashSet<>();
}

