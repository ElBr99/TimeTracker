package com.pet_project.time_tracker.service;

import com.pet_project.time_tracker.dto.CreateUserDto;
import com.pet_project.time_tracker.dto.UpdateInfoDto;
import com.pet_project.time_tracker.exceptions.UserNotFoundException;
import com.pet_project.time_tracker.mapper.CreateUserMapper;
import com.pet_project.time_tracker.model.User;
import com.pet_project.time_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Transactional
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;


    @Override
    public void addUser(@Validated CreateUserDto createUserDto) {

        userRepository.save(createUserMapper.userDtoToUser( createUserDto));
    }

    @Override
    public void changeInfo(Integer id, UpdateInfoDto updateInfoDto) {

        //Здесь не сказано реализовывать спринг security  и к тому же не смогу каррент юзера проставить, потому что не сказано аторизацию реалиховывать
        //id в метод может не передаваться. В этой реализации фронт айди будет давать, поскольку юзер чтобы сам себе данные обновить не должен вводить айди
        //

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (updateInfoDto.getName() != null) {
            user.setName(updateInfoDto.getName());

        }

        if (updateInfoDto.getLastName() != null) {
            user.setLastName(updateInfoDto.getLastName());
        }

        userRepository.save(user);
    }
}
