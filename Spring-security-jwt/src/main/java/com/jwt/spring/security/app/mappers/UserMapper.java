package com.jwt.spring.security.app.mappers;

import com.jwt.spring.security.app.model.ERole;
import com.jwt.spring.security.app.model.RoleEntity;
import com.jwt.spring.security.app.model.UserEntity;
import com.jwt.spring.security.app.request.UserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
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

    public static UserDetails toUserDetails(UserEntity user){
        List<SimpleGrantedAuthority>authorities=new ArrayList<>();

        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole().name()));
        });

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
