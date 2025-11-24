package com.example.newsletterservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="topics")
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String name;
    private String description;
    private Instant createdAt = Instant.now();

    @ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Subscriber> subscribers = new HashSet<>();
}

