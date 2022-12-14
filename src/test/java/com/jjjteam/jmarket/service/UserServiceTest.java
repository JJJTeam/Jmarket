package com.jjjteam.jmarket.service;

import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


@Slf4j
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    @Test
    void test() {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("roles : {}", roles);
    }
}