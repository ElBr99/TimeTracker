package com.pet_project.time_tracker.integration.controller;

import com.pet_project.time_tracker.integration.IntegrationTestBase;
import com.pet_project.time_tracker.model.Task;
import com.pet_project.time_tracker.model.User;
import com.pet_project.time_tracker.repository.TaskRepository;
import com.pet_project.time_tracker.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;


@AutoConfigureMockMvc
@Sql({"classpath:scripts/insert-data.sql"})
public class TasksRestControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void performTask_OK() throws Exception {

        List<Task> tasks = taskRepository.findAll();
        Optional<Task> foundTask = tasks.stream().filter(task -> task.getName().equals("taska1"))
                .filter(task -> task.getDescription().equals("redo"))
                .findFirst();

        Assertions.assertTrue(foundTask.isPresent());
        Integer taskId = foundTask.get().getId();


        List<User> users = userRepository.findAll();
        Optional<User> foundUser = users.stream().filter(user -> user.getName().equals("petr"))
                .filter(user -> user.getLastName().equals("petrov"))
                .findFirst();
        Assertions.assertTrue(foundUser.isPresent());

        Integer userId = foundUser.get().getId();
        String userIdStr = new ObjectMapper().writeValueAsString(userId);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tasks/{taskId}/perform", taskId)
                        .param("userId", userIdStr))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


        Assertions.assertEquals(userId, taskRepository.findById(taskId).get().getUser().getId());
        Assertions.assertEquals(OffsetDateTime.now().toLocalDate(), taskRepository.findById(taskId).get().getStartedAt().toLocalDate());
    }

    @Test
    void finishTask_OK() throws Exception {

        List<Task> tasks = taskRepository.findAll();
        Optional<Task> foundTask = tasks.stream().filter(task -> task.getName().equals("taska1"))
                .filter(task -> task.getDescription().equals("redo"))
                .findFirst();

        Assertions.assertTrue(foundTask.isPresent());
        Integer taskId = foundTask.get().getId();


        List<User> users = userRepository.findAll();
        Optional<User> foundUser = users.stream().filter(user -> user.getName().equals("petr"))
                .filter(user -> user.getLastName().equals("petrov"))
                .findFirst();
        Assertions.assertTrue(foundUser.isPresent());

        Integer userId = foundUser.get().getId();
        String userIdStr = new ObjectMapper().writeValueAsString(userId);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tasks/{taskId}/finish", taskId)
                        .param("userId", userIdStr))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


        Assertions.assertEquals(userId, taskRepository.findById(taskId).get().getUser().getId());
        Assertions.assertEquals(OffsetDateTime.now().toLocalDate(), taskRepository.findById(taskId).get().getStartedAt().toLocalDate());
    }


}
