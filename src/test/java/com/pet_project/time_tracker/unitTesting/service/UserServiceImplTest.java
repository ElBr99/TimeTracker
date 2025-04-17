package com.pet_project.time_tracker.unitTesting.service;


import com.pet_project.time_tracker.dto.CreateUserDto;
import com.pet_project.time_tracker.dto.UpdateInfoDto;
import com.pet_project.time_tracker.mapper.CreateUserMapper;
import com.pet_project.time_tracker.model.User;
import com.pet_project.time_tracker.repository.UserRepository;
import com.pet_project.time_tracker.service.UserServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateUserMapper createUserMapper;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void addUserTest_OK() {
        CreateUserDto createUserDto = new CreateUserDto("user", "user");

        userService.addUser(createUserDto);


        Mockito.verify(userRepository).save(any());
        Mockito.verify(createUserMapper).userDtoToUser(any());
    }

    @Test
    void addUserTest_ValidationFail() {
        CreateUserDto createUserDto = new CreateUserDto(null, "user");
        User user = User.builder()
                .name(createUserDto.getName())
                .role(null)
                .tasks(null)
                .headId(null)
                .lastName(createUserDto.getLastName())
                .build();

        when(createUserMapper.userDtoToUser(createUserDto))
                .thenReturn(user);
        when(userRepository.save(user))
                .thenThrow(ConstraintViolationException.class);

        assertThrows(ConstraintViolationException.class, () -> userService.addUser(createUserDto));

    }

    @Test
    void changeUserInfo() {

        Integer userId = 2;
        UpdateInfoDto updateInfoDto = new UpdateInfoDto("user", "userTest");
        User user = User.builder()
                .id(userId)
                .name("userr")
                .lastName("test")
                .build();

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        userService.changeInfo(2, updateInfoDto);

        assertNotNull(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

}
