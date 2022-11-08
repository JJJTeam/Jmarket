package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.dto.payload.request.SignUpRequest;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Transactional
    public Boolean existsByEmail(String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }

    @Transactional
    public void registerUser(SignUpRequest signUpRequest) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        User user = User.builder()
                .userId(signUpRequest.getUserid())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);
    }

}
