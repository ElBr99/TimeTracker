package com.pet_project.time_tracker.projections;

import java.time.LocalDateTime;


public interface TimeIntervalsByTasks {

    LocalDateTime getStartedAt();
    LocalDateTime getFinishedAt();
    String getName();


}
