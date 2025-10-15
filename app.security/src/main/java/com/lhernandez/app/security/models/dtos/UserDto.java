package com.lhernandez.app.security.models.dtos;

import java.util.Set;

import com.lhernandez.app.security.models.Role;

public class UserDto {

    private String username;
    private String email;
    private String password;
    private Set<Role> roles;

    private UserDto(){
        
    }

    private UserDto(Builder builder){
        this.username=builder.username;
        this.email=builder.email;
        this.password=builder.password;
        this.roles=builder.roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRol(Role role) {
        this.roles.add(role);
    }

    public static class Builder {

        private String username;
        private String email;
        private String password;
        private Set<Role> roles;

        public Builder username(String username){
            this.username=username;
            return this;
        }

        public Builder email(String email){
            this.email=email;
            return this;
        }

        public Builder password(String password){
            this.password=password;
            return this;
        }

        public Builder roles(Set<Role>roles){
            this.roles=roles;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }

    }

}
