package com.pet_project.time_tracker.mapper;


import com.pet_project.time_tracker.dto.CreateUserDto;
import com.pet_project.time_tracker.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUserMapper {

    User userDtoToUser (CreateUserDto createUserDto);






}
