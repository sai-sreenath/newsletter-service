package com.example.newsletterservice.service;

import com.example.newsletterservice.dto.CreateContentRequestDTO;
import com.example.newsletterservice.jobs.DispatchContentJob;
import com.example.newsletterservice.model.*;
import com.example.newsletterservice.repository.ContentRepository;
import com.example.newsletterservice.repository.SendLogRepository;
import com.example.newsletterservice.repository.SubscriberRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ContentService {
    private final ContentRepository contentRepo;
    private final SubscriberRepository subscriberRepo;
    private final Scheduler scheduler;
    private final SendLogRepository sendLogRepo;
    private final EmailService emailService;

    @Autowired
    public ContentService(ContentRepository contentRepo, SubscriberRepository subscriberRepo,
                          Scheduler scheduler, SendLogRepository sendLogRepo, EmailService emailService) {
        this.contentRepo = contentRepo;
        this.subscriberRepo = subscriberRepo;
        this.scheduler = scheduler;
        this.sendLogRepo = sendLogRepo;
        this.emailService = emailService;
    }

    @Transactional
    public Content createAndSchedule(CreateContentRequestDTO requestDTO) {
        Content content = new Content();
        content.setSubject(requestDTO.subject());
        content.setBody(requestDTO.body());
        content.setScheduledAt(requestDTO.scheduledAt());
        content.setStatus(ContentStatus.SCHEDULED);

        Topic topic = new Topic();
        topic.setId(requestDTO.topicId());
        content.setTopic(topic);
        content = contentRepo.save(content);
        scheduleJob(content);

        return content;
    }

    private void scheduleJob(Content content) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("contentId", content.getId());

        JobDetail job = JobBuilder.newJob(DispatchContentJob.class)
                .withIdentity("content-" + content.getId(), "content-jobs")
                .usingJobData(jobDataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger-content-" + content.getId(), "content-triggers")
                .startAt(Date.from(content.getScheduledAt()))
                .forJob(job)
                .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch(SchedulerException e) {
            throw new RuntimeException("Failed to schedule job", e);
        }

    }

    public void dispatchContent(Long contentId) {
        Content content = contentRepo.findById(contentId).orElse(null);

        if (content == null) return;

        // Add null check for topic
        if (content.getTopic() == null) {
            // Handle error - content without topic
            return;
        }

        List<Subscriber> subs = subscriberRepo.findByTopics_IdAndActiveTrue(content.getTopic().getId());

        for (Subscriber s : subs) {
            try {
                emailService.sendHtml(s.getEmail(), content.getSubject(), content.getBody());
                SendLog log = new SendLog();
                log.setContent(content);
                log.setSubscriber(s);
                log.setSentAt(Instant.now());
                log.setStatus(SendStatus.SUCCESS);
                sendLogRepo.save(log);
            } catch (Exception ex) {
                SendLog log = new SendLog();
                log.setContent(content);
                log.setSubscriber(s);
                log.setSentAt(Instant.now());
                log.setStatus(SendStatus.FAILED);
                log.setError(ex.getMessage());
                sendLogRepo.save(log);
            }
        }

        content.setStatus(ContentStatus.SENT);
        contentRepo.save(content);
    }

    public List<Content> getAllContent() {
        return contentRepo.findAll();
    }
}
