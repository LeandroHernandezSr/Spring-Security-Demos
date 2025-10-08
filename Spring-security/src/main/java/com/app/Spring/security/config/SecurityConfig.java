package com.app.Spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                // Cuales urls estaran protegidas y cuales no
                .authorizeHttpRequests(auth -> {
                    // Endpoints publicos
                    auth.requestMatchers("/v1/index").permitAll();
                    // Endpoints privados
                    auth.requestMatchers("/v1/index2").authenticated();

                    auth.anyRequest().authenticated();

                }).formLogin(form -> 
                    form.successHandler(successHandler())
                ).sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                    session.invalidSessionUrl("/login");
                    session.maximumSessions(1).expiredUrl("/login").sessionRegistry(sessionRegistry());
                    session.sessionFixation().newSession();
                })
                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
            }

    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/v1/session");
        };
    }

}
