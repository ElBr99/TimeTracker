package com.pet_project.time_tracker.unitTesting.controllers;

import com.pet_project.time_tracker.controller.UserRestController;
import com.pet_project.time_tracker.dto.CreateUserDto;
import com.pet_project.time_tracker.dto.UpdateInfoDto;
import com.pet_project.time_tracker.model.User;
import com.pet_project.time_tracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @MockitoBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void savingUser_OK() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("qwerty", "qwertyui");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(userService, Mockito.times(1)).addUser(createUserDto);
    }

    @Test
    void updateUser_OK() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        User userForUpdate = User.builder()
                .id(1)
                .name("qwer")
                .lastName("qwerty")
                .build();

        UpdateInfoDto updateInfoDto = new UpdateInfoDto(null, "qwertty");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v2/users/{id}", userForUpdate.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateInfoDto)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(userService, Mockito.times(1)).changeInfo(userForUpdate.getId(), updateInfoDto);


    }


}
