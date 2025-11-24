package com.example.newsletterservice.dto;

import java.util.List;

public record CreateSubscriberRequestDTO(String email, List<Long> topicIds) {
}
