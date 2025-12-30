package com.corpevent.corpevent.config;

import com.corpevent.corpevent.enums.RoleName;
import com.corpevent.corpevent.model.Role;
import com.corpevent.corpevent.model.User;
import com.corpevent.corpevent.repos.RoleRepository;
import com.corpevent.corpevent.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class InitialDataRunner implements CommandLineRunner {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        for(RoleName roleName: RoleName.values()){
            roleRepo.findByName(roleName).orElseGet(()->{
                Role role = Role.builder()
                        .name(roleName)
                        .build();
                return roleRepo.save(role);
            });
        }

        if(userRepo.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepo.findByName(RoleName.ADMIN_USER)
                    .orElseThrow(() -> new RuntimeException("Role ADMIN_USER not found"));

            User adminUser = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .firstname("Administrator")
                    .lastname("Utilizatiori")
                    .email("admin@corpevent.com")
                    .enabled(true)
                    .roles(Set.of(adminRole))
                    .build();

            userRepo.save(adminUser);

            System.out.println("=== INITIAL ADMIN USER CREATED: username=admin / password=admin ===");
        }
    }
}
