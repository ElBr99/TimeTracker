package com.pet_project.time_tracker.integration.controller;


import com.pet_project.time_tracker.dto.TasksByTime;
import com.pet_project.time_tracker.integration.IntegrationTestBase;
import com.pet_project.time_tracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@AutoConfigureMockMvc
@Sql({"classpath:scripts/insert-reportData.sql"})
public class ReportControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @Test
    void getTimeConsumedTest_OK() throws Exception {

        Integer id = 2;
        String strId = new ObjectMapper().writeValueAsString(id);

        List<TasksByTime> expectedTasks = List.of(new TasksByTime(1, "taska1", 13, 13),
                new TasksByTime(2, "taska2", 13, 5)
        );

        String expectedTasksByTime = new ObjectMapper().writeValueAsString(expectedTasks);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reports/getTimeConsumed")
                        .param("userId", strId))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedTasksByTime))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }


}
