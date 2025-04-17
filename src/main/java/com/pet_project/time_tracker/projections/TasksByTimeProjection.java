package com.pet_project.time_tracker.projections;


public interface TasksByTimeProjection {
    Integer getId();
    String getName();
    Integer getMinutesSpent();
    Integer getDaysSpent();


}
