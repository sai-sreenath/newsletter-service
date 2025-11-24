package com.example.newsletterservice.controller;

import com.example.newsletterservice.model.Topic;
import com.example.newsletterservice.repository.TopicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final TopicRepository topicRepo;

    public TopicController(TopicRepository topicRepo) {
        this.topicRepo = topicRepo;
    }

    @PostMapping
    public ResponseEntity<Topic> create(@RequestBody Topic topic) {
        Topic saved = topicRepo.save(topic);
        return ResponseEntity.created(URI.create("/api/topics/" + saved.getId())).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(topicRepo.findAll());
    }
}
