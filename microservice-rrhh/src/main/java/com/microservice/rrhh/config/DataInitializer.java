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
        // Solo insertamos si no hay roles en la BD
        if (roleRepository.count() == 0) {
            List<Role> roles = List.of(
                new Role(null, "ADMINISTRADOR GENERAL"),
                new Role(null, "ADMINISTRADOR RRHH"),
                new Role(null, "ADMINISTRADOR LOGISTICA"),
                new Role(null, "ADMINISTRADOR VENTAS"),
                new Role(null, "ADMINISTRADOR PLANEACION"),
                new Role(null, "ADMINISTRADOR CRM"),
                new Role(null, "COORDINADOR CRM"),
                new Role(null, "ASESOR CRM")
            );

            roleRepository.saveAll(roles);
            System.out.println("✅ Roles iniciales creados.");
        } else {
            System.out.println("ℹ️ Ya existen Roles, no se insertan duplicados.");
        }
    }
}