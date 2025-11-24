package com.example.newsletterservice.dto;

import java.time.Instant;

public record CreateContentRequestDTO(Long topicId, String subject, String body, Instant scheduledAt) {

}
