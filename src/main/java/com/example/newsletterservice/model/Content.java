package com.example.newsletterservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name="contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Topic topic;

    @Column(nullable=false)
    private String subject;

    private String body;

    @Column(nullable = false)
    private Instant scheduledAt;

    @Enumerated(EnumType.STRING)
    private ContentStatus status = ContentStatus.SCHEDULED;

    private Instant createdAt = Instant.now();
}

