package com.pet_project.time_tracker.service;

import com.pet_project.time_tracker.exceptions.TaskNotFoundException;
import com.pet_project.time_tracker.exceptions.UserNotFoundException;
import com.pet_project.time_tracker.model.Status;
import com.pet_project.time_tracker.model.Task;
import com.pet_project.time_tracker.model.User;
import com.pet_project.time_tracker.repository.TaskRepository;
import com.pet_project.time_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Transactional
@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public void performTask(Integer taskId, Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Такого пользователя не найдено"));

        Task taskFromBD = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Такой задачи не существует"));

        taskFromBD.setStartedAt(OffsetDateTime.now());
        taskFromBD.setStatus(Status.IN_PROGRESS);
        taskFromBD.setUser(user);

        taskRepository.save(taskFromBD);
    }

    @Override
    public void finishTask(Integer taskId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Такого пользователя не найдено"));

        Task taskFromBD = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Такой задачи не существует"));

        taskFromBD.setFinishedAt(OffsetDateTime.now());
        taskFromBD.setStatus(Status.COMPLETED);
        taskFromBD.setUser(user);

        taskRepository.save(taskFromBD);


    }
}
