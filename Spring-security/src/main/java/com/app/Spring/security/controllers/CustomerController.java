package com.app.Spring.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/index")
    public ResponseEntity<String> index(){
       return new ResponseEntity<>("Hello world!",HttpStatus.OK); 
    }

    @GetMapping("/index2")
    public ResponseEntity<String> index2(){
        return new ResponseEntity<>("Hello world NOT SECURED!",HttpStatus.OK);
    }

    @GetMapping("/session")
    public ResponseEntity<?> getDetailsSession(){
        AtomicReference<String> sessionId= new AtomicReference<>("");
        AtomicReference<User> userObject= new AtomicReference<>();

        List<Object> sessions=sessionRegistry.getAllPrincipals();

        sessions.forEach(session -> {
            if(session instanceof User){
                userObject.set((User) session);
            }

            List<SessionInformation>sessionInformations=sessionRegistry.getAllSessions(userObject.get(),false);

            sessionInformations.forEach(sessionInformation -> {
               sessionId.set(sessionInformation.getSessionId());
            });
        });

        Map<String,Object> map=new HashMap<>();

        map.put("response","Hello world!");
        map.put("sessionId",sessionId.get());
        map.put("user",userObject.get());

        return new  ResponseEntity<>(map,HttpStatus.OK);
    }
}
