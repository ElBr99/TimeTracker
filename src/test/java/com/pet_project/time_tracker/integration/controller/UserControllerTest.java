package com.pet_project.time_tracker.integration.controller;

import com.pet_project.time_tracker.dto.CreateUserDto;
import com.pet_project.time_tracker.dto.UpdateInfoDto;
import com.pet_project.time_tracker.integration.IntegrationTestBase;
import com.pet_project.time_tracker.model.User;
import com.pet_project.time_tracker.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;

@AutoConfigureMockMvc
@Sql ({"classpath:scripts/insert-data.sql"})
public class UserControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserTest_OK() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("ivan", "ivanov");
        String jsonCreateUser = new ObjectMapper().writeValueAsString(createUserDto);

        //передать надо сериализованный объект
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreateUser))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        List<User> users = userRepository.findAll();
        Optional<User> foundUser = users.stream().filter(user -> user.getName().equals(createUserDto.getName()))
                .filter(user -> user.getLastName().equals(createUserDto.getLastName()))
                .findFirst();

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertNotNull(users);
        Assertions.assertEquals(foundUser.get().getName(), createUserDto.getName());
        Assertions.assertEquals(foundUser.get().getLastName(), createUserDto.getLastName());

    }

    @Test
    void saveUserTest_NotValidNullField() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto(null, "ivanov");
        String jsonCreateUser = new ObjectMapper().writeValueAsString(createUserDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreateUser))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    //написать кастомный валидатор
    @Test
    void updateUserTest_OK() throws Exception {


        UpdateInfoDto updateInfoDto = new UpdateInfoDto("svetik", null);

        List<User> users = userRepository.findAll();
        Optional<User> foundUser = users.stream().filter(user -> user.getName().equals("sveta"))
                .findFirst();

        Assertions.assertTrue(foundUser.isPresent());
        Integer id = foundUser.get().getId();

        String jsonUpdateUser = new ObjectMapper().writeValueAsString(updateInfoDto);


        mockMvc.perform(MockMvcRequestBuilders.put("/api/v2/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdateUser))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Optional<User> user = userRepository.findById(id);
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("svetik", user.get().getName());

    }


}
