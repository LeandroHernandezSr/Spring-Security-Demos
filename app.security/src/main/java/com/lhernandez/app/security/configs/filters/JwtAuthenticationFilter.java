package com.lhernandez.app.security.configs.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhernandez.app.security.configs.jwt.JwtUtils;
import com.lhernandez.app.security.models.entities.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String username="";
    private String password="";
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserEntity user=null;
        try{
            user=new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            this.username=user.getUsername();
            this.password=user.getPassword();
        }catch (Exception e){
            throw new AuthenticationServiceException(e.getMessage());
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user=(User) authResult.getPrincipal();

        Map<String,Object> map=new HashMap<>();

        String token=jwtUtils.generateAccessToken(user.getUsername());

        map.put("username",user.getUsername());
        map.put("token",token);
        map.put("message","Authentication Success");

        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
