package com.example.newsletterservice.jobs;

import com.example.newsletterservice.service.ContentService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatchContentJob implements Job {
    private final ContentService contentService;

    @Autowired
    public DispatchContentJob(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Long contentId = context.getMergedJobDataMap().getLong("contentId");
        contentService.dispatchContent(contentId);
    }
}
