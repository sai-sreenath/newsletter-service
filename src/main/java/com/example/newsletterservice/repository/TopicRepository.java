package com.example.newsletterservice.repository;

import com.example.newsletterservice.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    Optional<Topic> findByName(String name);
}

