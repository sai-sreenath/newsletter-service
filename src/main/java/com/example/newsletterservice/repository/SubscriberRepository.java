package com.example.newsletterservice.repository;

import com.example.newsletterservice.model.Subscriber;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    @EntityGraph(attributePaths = "topics")
    @Query("""
       SELECT DISTINCT s
       FROM Subscriber s
       JOIN s.topics t
       WHERE t.id = :topicId AND s.active = true
       """)
    List<Subscriber> findByTopics_IdAndActiveTrue(@Param("topicId") Long topicId);

    @EntityGraph(attributePaths = "topics")
    @Query("SELECT s FROM Subscriber s")
    List<Subscriber> findAllWithTopics();

    @EntityGraph(attributePaths = "topics")
    @Query("select s from Subscriber s left join fetch s.topics where s.email = :email")
    Optional<Subscriber> findByEmailWithTopics(@Param("email") String email);

    @EntityGraph(attributePaths = "topics")
    @Query("SELECT s FROM Subscriber s WHERE s.id = :id")
    Optional<Subscriber> findByIdWithTopics(@Param("id") Long id);
}
