package com.lhernandez.app.security.services;

import com.lhernandez.app.security.models.dtos.UserDto;

public interface IUserService {
    UserDto createUser(UserDto userDto);
}
