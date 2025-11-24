package com.example.newsletterservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name="send_log")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SendLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    private Instant sentAt;

    @Enumerated(EnumType.STRING)
    private SendStatus status;

    @Column(columnDefinition="text")
    private String error;

    private String providerMessageId;
}
