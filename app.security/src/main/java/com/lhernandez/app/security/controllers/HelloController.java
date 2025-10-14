package com.lhernandez.app.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello-not-secured")
    public ResponseEntity<String>helloNotSecured(){
        return new ResponseEntity<>("Hello World! Not securd",HttpStatus.OK);
    }

    @GetMapping("/hello-secured")
    public ResponseEntity<String>helloSecured(){
        return new ResponseEntity<>("Hello World! Secured",HttpStatus.OK);
    }

}
