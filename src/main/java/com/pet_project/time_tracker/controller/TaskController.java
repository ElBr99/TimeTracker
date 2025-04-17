package com.pet_project.time_tracker.controller;


import com.pet_project.time_tracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;


    @PutMapping("/{taskId}/perform")
    @ResponseStatus(HttpStatus.OK)
    public void performTask(@PathVariable Integer taskId, @RequestParam Integer userId) {
        taskService.performTask(taskId, userId);
    }

    @PutMapping("/{taskId}/finish")
    @ResponseStatus(HttpStatus.OK)
    public void finishTask(@PathVariable Integer taskId, @RequestParam Integer userId) {
        taskService.finishTask(taskId, userId);
    }

}
