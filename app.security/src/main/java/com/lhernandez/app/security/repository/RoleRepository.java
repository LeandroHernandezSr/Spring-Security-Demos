package com.lhernandez.app.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhernandez.app.security.model.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{

}
