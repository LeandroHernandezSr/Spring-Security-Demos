package com.lhernandez.app.security.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.lhernandez.app.security.models.entities.UserEntity;


public class UserMapper {

    private UserMapper(){}
    
    public static UserDetails toUserDetails(UserEntity userEntity){

        List<SimpleGrantedAuthority>authorities=new ArrayList<>();

        userEntity.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        });

        return User.builder()
            .username(userEntity.getUsername())
            .password(userEntity.getPassword())
            .authorities(authorities)
            .build();
    }

}
