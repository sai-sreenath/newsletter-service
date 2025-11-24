package com.example.newsletterservice.repository;

import com.example.newsletterservice.model.SendLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendLogRepository extends JpaRepository<SendLog,Long> {
}
