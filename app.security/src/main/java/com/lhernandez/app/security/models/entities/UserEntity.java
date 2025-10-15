package com.lhernandez.app.security.models.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity>roles;

    private UserEntity(Builder builder){
        this.username=builder.username;
        this.email=builder.email;
        this.password=builder.password;
        this.roles=builder.roles;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public static class Builder{
        
        private String username;
        private String email;
        private String password;
        private Set<RoleEntity>roles;

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

        public Builder roles(Set<RoleEntity>roles){
            this.roles=roles;
            return this;
        }


        public UserEntity build(){
            return new UserEntity(this);
        }
    }
    
}
