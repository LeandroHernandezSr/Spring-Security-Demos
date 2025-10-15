package com.lhernandez.app.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lhernandez.app.security.mappers.UserMapper;
import com.lhernandez.app.security.models.entities.UserEntity;
import com.lhernandez.app.security.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity>userEntityOpt=userRepository.findByUsername(username);
        
        if(userEntityOpt.isPresent()){
            return UserMapper.toUserDetails(userEntityOpt.get());
        }

        return null;
    }

}
