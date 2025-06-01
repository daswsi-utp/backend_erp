package com.microservice.rrhh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
