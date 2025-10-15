package com.lhernandez.app.security.services;

import com.lhernandez.app.security.mappers.UserMapper;
import com.lhernandez.app.security.models.dtos.UserDto;
import com.lhernandez.app.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository=repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return UserMapper.toDto(this.repository.save(UserMapper.toEntity(userDto)));
    }
    
}
