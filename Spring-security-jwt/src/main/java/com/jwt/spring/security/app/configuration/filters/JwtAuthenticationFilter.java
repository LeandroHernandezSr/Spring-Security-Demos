package com.jwt.spring.security.app.configuration.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.spring.security.app.configuration.jwt.JwtUtils;
import com.jwt.spring.security.app.model.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public String username="";
    public String password="";
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserEntity user=null;
        try{
            user= new ObjectMapper().readValue(request.getInputStream(),UserEntity.class);
            this.username = user.getUsername();
            this.password=user.getPassword();

        }catch (Exception ignored){
            throw new RuntimeException(ignored.getMessage());
        }

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);


        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user=(User) authResult.getPrincipal();

        String token=jwtUtils.generateAcessToken(user.getUsername());

        Map<String,Object> httpResponse=new HashMap<>();

        httpResponse.put("token",token);
        httpResponse.put("Message","Authentication Success");
        httpResponse.put("Username",user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));

        response.setStatus(HttpStatus.OK.value());

        response.setContentType("application/json");

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
