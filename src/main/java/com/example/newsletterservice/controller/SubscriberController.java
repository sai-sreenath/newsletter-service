package com.example.newsletterservice.controller;

import com.example.newsletterservice.dto.CreateSubscriberRequestDTO;
import com.example.newsletterservice.model.Subscriber;
import com.example.newsletterservice.model.Topic;
import com.example.newsletterservice.repository.SubscriberRepository;
import com.example.newsletterservice.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {
    private final SubscriberRepository subRepo;
    private final TopicRepository topicRepo;

    @Autowired
    public SubscriberController(SubscriberRepository subRepo, TopicRepository topicRepo) {
        this.subRepo = subRepo; this.topicRepo = topicRepo;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Subscriber> subscribe(@RequestBody CreateSubscriberRequestDTO req) {
        Subscriber subscriber = subRepo.findByEmailWithTopics(req.email())
                .orElseGet(() -> {
                    Subscriber newSub = new Subscriber();
                    newSub.setEmail(req.email());
                    return newSub;
                });

        if (req.topicIds() != null) {
            List<Topic> fetched = topicRepo.findAllById(req.topicIds());

            Set<Topic> currentTopics = subscriber.getTopics();

            if (currentTopics == null) {
                currentTopics = new HashSet<>();
                subscriber.setTopics(currentTopics);
            }

            currentTopics.clear();
            currentTopics.addAll(fetched);
        }
        Subscriber saved = subRepo.save(subscriber);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(subRepo.findAllWithTopics());
    }

    @GetMapping("/{id}/topics")
    public ResponseEntity<Set<Topic>> getSubscriberTopics(@PathVariable Long id) {
        return subRepo.findByIdWithTopics(id)
                .map(subscriber -> ResponseEntity.ok(subscriber.getTopics()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/topics")
    @Transactional
    public ResponseEntity<Subscriber> addTopicsToSubscriber(
            @PathVariable Long id,
            @RequestBody List<Long> topicIds) {
        return subRepo.findByIdWithTopics(id)
                .map(subscriber -> {
                    if (topicIds != null) {
                        topicIds.forEach(topicId ->
                                topicRepo.findById(topicId)
                                        .ifPresent(topic -> subscriber.getTopics().add(topic))
                        );
                    }
                    return ResponseEntity.ok(subRepo.save(subscriber));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/topics")
    @Transactional
    public ResponseEntity<Subscriber> unsubscribeFromTopics(
            @PathVariable Long id,
            @RequestBody List<Long> topicIds) {
        return subRepo.findByIdWithTopics(id)
                .map(subscriber -> {
                    if (topicIds != null) {
                        topicIds.forEach(topicId ->
                                subscriber.getTopics().removeIf(topic -> topic.getId().equals(topicId))
                        );
                    }
                    return ResponseEntity.ok(subRepo.save(subscriber));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{subscriberId}/topics/{topicId}")
    @Transactional
    public ResponseEntity<Subscriber> unsubscribeFromTopic(@PathVariable Long subscriberId,
                                                           @PathVariable Long topicId) {
        return subRepo.findByIdWithTopics(subscriberId)
                .map(subscriber -> {
                    subscriber.getTopics().removeIf(topic -> topic.getId().equals(topicId));
                    return ResponseEntity.ok(subRepo.save(subscriber));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscriber> getSubscriber(@PathVariable Long id) {
        return subRepo.findByIdWithTopics(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}