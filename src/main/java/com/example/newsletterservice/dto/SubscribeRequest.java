package com.example.newsletterservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubscribeRequest {
    private String email;
    private List<String> topics;
}
