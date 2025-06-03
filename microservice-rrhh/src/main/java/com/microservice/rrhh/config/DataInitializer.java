package com.microservice.rrhh.config;


import com.microservice.rrhh.model.Role;
import com.microservice.rrhh.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            List<Role> roles = List.of(
                new Role(null, "ADMIN"),
                new Role(null, "ADMIN_RRHH"),
                new Role(null, "ADMIN_LOGISTIC"),
                new Role(null, "ADMIN_VENTAS"),
                new Role(null, "ADMIN_PLANNING"),
                new Role(null, "ADMIN_MANUFACTURE"),
                new Role(null, "ADMIN_CRM"),
                new Role(null, "COORDINATOR_CRM"),
                new Role(null, "ASESOR_CRM")
            );

            roleRepository.saveAll(roles);
            System.out.println("✅ Roles iniciales creados.");
        } else {
            System.out.println("ℹ️ Ya existen Roles, no se insertan duplicados.");
        }
    }
}