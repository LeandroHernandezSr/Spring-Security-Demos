package com.lhernandez.app.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhernandez.app.security.models.dtos.UserDto;
import com.lhernandez.app.security.services.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService=userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?>createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.OK);
    }

}
