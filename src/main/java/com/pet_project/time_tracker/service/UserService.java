package com.pet_project.time_tracker.service;

import com.pet_project.time_tracker.dto.CreateUserDto;
import com.pet_project.time_tracker.dto.UpdateInfoDto;

public interface UserService {

    void addUser (CreateUserDto createUserDto);

    void changeInfo (Integer id, UpdateInfoDto updateInfoDto);



}
