package com.lhernandez.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception{
        https
        .csrf(c->c.disable())
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(http->{
            http.requestMatchers("/api/hello-not-secured").permitAll();
            http.anyRequest().authenticated();
        });

        return https.build();
    }

}
