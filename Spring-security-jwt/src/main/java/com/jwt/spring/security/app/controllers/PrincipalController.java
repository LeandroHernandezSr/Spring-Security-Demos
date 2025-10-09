package com.jwt.spring.security.app.controllers;

import com.jwt.spring.security.app.handler.UserHandler;
import com.jwt.spring.security.app.request.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class PrincipalController {

    private final UserHandler userHandler;

    public PrincipalController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello world! -Not secured", HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?>createUser(@Valid @RequestBody UserDto userDto) {
        this.userHandler.createUser(userDto);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestBody String id) {
        return new ResponseEntity<>("User "+id+" delete: "+this.userHandler.deleteUser(id), HttpStatus.OK);
    }
}
