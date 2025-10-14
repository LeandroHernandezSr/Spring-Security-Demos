package com.lhernandez.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhernandez.app.security.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
    Optional<UserEntity> findByUsername(String username);
}
