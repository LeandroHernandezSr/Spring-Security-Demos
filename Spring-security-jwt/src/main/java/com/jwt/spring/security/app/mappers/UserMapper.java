package com.jwt.spring.security.app.mappers;

import com.jwt.spring.security.app.model.ERole;
import com.jwt.spring.security.app.model.RoleEntity;
import com.jwt.spring.security.app.model.UserEntity;
import com.jwt.spring.security.app.request.UserDto;

import java.util.stream.Collectors;


public class UserMapper {
    public static UserEntity toEntity(UserDto userDto){
        return  UserEntity.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .roles(userDto.getRoles().stream().map(role->{
                    return RoleEntity.builder().role(ERole.valueOf(role)).build();
                }).collect(Collectors.toSet())).build();
    }
}
