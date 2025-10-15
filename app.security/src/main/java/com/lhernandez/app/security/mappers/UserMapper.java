package com.lhernandez.app.security.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.lhernandez.app.security.models.dtos.UserDto;
import com.lhernandez.app.security.models.entities.RoleEntity;
import com.lhernandez.app.security.models.entities.UserEntity;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDetails toUserDetails(UserEntity userEntity) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(authorities)
                .build();
    }
    
    public static UserEntity toEntity(UserDto userDto) {
        return new UserEntity.Builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roles(userDto.getRoles().stream().map(rol -> {
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setRole(rol);
                    return roleEntity;
                }).collect(Collectors.toSet()))
                .build();
    }


    public static UserDto toDto(UserEntity userEntity){
        return new UserDto.Builder()
            .username(userEntity.getUsername())
            .email(userEntity.getEmail())
            .password(userEntity.getPassword())
            .roles(userEntity.getRoles().stream().map(role->role.getRole()).collect(Collectors.toSet()))
            .build();
    }

}
