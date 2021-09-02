package com.example.example.task;

import io.sentry.Sentry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CustomTask {

    @Scheduled(fixedRate = 2000)
    public void someTask() {
        try {
            System.out.println("Starting some task");
            throw new RuntimeException("This is a test (manually capturing)");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
    }

}
