package com.example.newsletterservice.controller;

import com.example.newsletterservice.dto.CreateContentRequestDTO;
import com.example.newsletterservice.model.Content;
import com.example.newsletterservice.model.Topic;
import com.example.newsletterservice.repository.TopicRepository;
import com.example.newsletterservice.service.ContentService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentService contentService;
    private final TopicRepository topicRepo;

    @Autowired
    public ContentController(ContentService contentService, TopicRepository topicRepo) {
        this.contentService = contentService;
        this.topicRepo = topicRepo;
    }

    @PostMapping
    public ResponseEntity<Content> create(@RequestBody CreateContentRequestDTO requestDTO) throws SchedulerException {
        Optional<Topic> topic = topicRepo.findById(requestDTO.topicId());

        if (topic.isEmpty())
            return ResponseEntity.badRequest().build();

        Content saved = contentService.createAndSchedule(requestDTO);

        return ResponseEntity.created(URI.create("/api/content/" + saved.getId())).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(contentService.getAllContent());
    }
}
