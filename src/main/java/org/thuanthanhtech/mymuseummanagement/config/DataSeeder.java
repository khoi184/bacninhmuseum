package org.thuanthanhtech.mymuseummanagement.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import org.thuanthanhtech.mymuseummanagement.entity.Role;
import org.thuanthanhtech.mymuseummanagement.entity.User;
import org.thuanthanhtech.mymuseummanagement.repository.RoleRepository;
import org.thuanthanhtech.mymuseummanagement.repository.UserRepository;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // vai tro
        Role role = new Role();
        if (roleRepository.findByNameRole("ROLE_ADMIN") == null) {
            role.setNameRole("ROLE_ADMIN");
            roleRepository.save(role);
        }
        if (roleRepository.findByNameRole("ROLE_USER") == null) {
            role.setNameRole("ROLE_USER");
            roleRepository.save(role);
        }

        // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder().encode("123456"));
            admin.setUsername("admin");
            admin.setPhone("123456789");
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByNameRole("ROLE_ADMIN"));
            admin.setRole(roles);
            userRepository.save(admin);
        }

        // User account
        if (userRepository.findByEmail("user@gmail.com") == null) {
            User user = new User();
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder().encode("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByNameRole("ROLE_USER"));
            user.setRole(roles);
            userRepository.save(user);
        }
    }
}