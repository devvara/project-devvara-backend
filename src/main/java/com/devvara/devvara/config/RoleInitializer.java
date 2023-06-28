package com.devvara.devvara.config;

import com.devvara.devvara.domain.Role;
import com.devvara.devvara.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role userRole = new Role();
                userRole.setId(1L);
                userRole.setName("ROLE_USER");

                Role adminRole = new Role();
                adminRole.setId(2L);
                adminRole.setName("ROLE_ADMIN");

                roleRepository.save(userRole);
                roleRepository.save(adminRole);
            }
        };
    }

}
