package com.pet_project.time_tracker.controller;


import com.pet_project.time_tracker.dto.CreateUserDto;
import com.pet_project.time_tracker.dto.UpdateInfoDto;
import com.pet_project.time_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Validated CreateUserDto createUserDto) {

        userService.addUser(createUserDto);
    }


    @PutMapping("/{id}")
    public void updateUser(@RequestBody UpdateInfoDto updateInfoDto, @PathVariable Integer id) {
        userService.changeInfo(id, updateInfoDto);

    }
}
