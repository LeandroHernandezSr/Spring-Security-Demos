package com.jwt.spring.security.app.handler;

import com.jwt.spring.security.app.mappers.UserMapper;
import com.jwt.spring.security.app.repository.UserRepository;
import com.jwt.spring.security.app.request.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {

    private final UserRepository userRepository;

    private final PasswordEncoder  passwordEncoder;

    public UserHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println("PASSWORD: " + userDto.getPassword());
        this.userRepository.save(UserMapper.toEntity(userDto));
    }

    public boolean deleteUser(String id) {
        var user=this.userRepository.findById(Long.parseLong(id));

        if(user.isPresent()){
            this.userRepository.delete(user.get());
            return true;
        }

        return false;
    }
}
