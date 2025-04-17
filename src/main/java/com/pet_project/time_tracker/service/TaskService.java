package com.pet_project.time_tracker.service;

public interface TaskService {
    void performTask(Integer id, Integer userId);

    void finishTask(Integer id, Integer userId);
}
