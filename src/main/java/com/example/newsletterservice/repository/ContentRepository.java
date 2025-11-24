package com.example.newsletterservice.repository;

import com.example.newsletterservice.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content,Long> {
}
