package com.pet_project.time_tracker.scheduler;


import com.pet_project.time_tracker.model.Task;
import com.pet_project.time_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinishTaskScheduler {

    private final TaskRepository taskRepository;


    @Scheduled(cron = "0 59 23 * * *")
    public void finishTaskAuto() {
      List<Task> taskList = taskRepository.findAll();
      for(Task task : taskList) {
          task.setFinishedAt(OffsetDateTime.now());
          taskRepository.save(task);
      }
    }


}
