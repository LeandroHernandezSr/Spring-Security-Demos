package com.app.Spring.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class CustomerController {

    @GetMapping("/index")
    public ResponseEntity<String> index(){
       return new ResponseEntity<>("Hello world!",HttpStatus.OK); 
    }

    @GetMapping("/index2")
    public ResponseEntity<String> index2(){
        return new ResponseEntity<>("Hello world NOT SECURED!",HttpStatus.OK);
    }
}
