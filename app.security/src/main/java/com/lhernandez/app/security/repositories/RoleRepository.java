package com.lhernandez.app.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhernandez.app.security.models.entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{

}
