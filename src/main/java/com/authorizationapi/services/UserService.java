package com.authorizationapi.services;

import com.authorizationapi.dtos.UserDto;
import com.authorizationapi.entities.UserEntity;
import com.authorizationapi.exceptions.UserAlreadyExistException;
import com.authorizationapi.exceptions.UserNotFoundException;
import com.authorizationapi.exceptions.UsersNotFoundException;
import com.authorizationapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity createUser(UserEntity userEntity) throws UserAlreadyExistException {
        if (userRepository.findByLogin(userEntity.getLogin()) != null ) {
            throw new UserAlreadyExistException("Пользователь с таким login уже существует");
        }
        return userRepository.save(userEntity);
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).get();
        if(userEntity == null) {
            throw new UserNotFoundException("Пользователя с данным id не существует");
        }
        return UserDto.toDto(userEntity);
    }

    public List<UserDto> getAllUsers() throws UsersNotFoundException {
        List<UserEntity> usersEntity = (List<UserEntity>) userRepository.findAll();
        if (usersEntity == null) {
            throw new UsersNotFoundException("Пользователей нет");
        }
        List<UserDto> userDtos = convertEntityToDto(usersEntity);
        return userDtos;
    }

    private List<UserDto> convertEntityToDto(List<UserEntity> userEntities){
        return userEntities.stream()
                .map(userEntity -> UserDto.toDto(userEntity)).collect(Collectors.toList());
    }
}
