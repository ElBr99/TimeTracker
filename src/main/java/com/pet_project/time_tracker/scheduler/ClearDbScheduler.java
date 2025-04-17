package com.pet_project.time_tracker.scheduler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClearDbScheduler {

    @PersistenceContext
    private final EntityManager entityManager;


    @Scheduled(cron = "${cron.value}")
    public void clearDb () {
        entityManager.createNativeQuery("truncate tasks, users ").executeUpdate();

    }
}
