package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.model.ERole;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.payload.request.SignupRequest;
import com.jjjteam.jmarket.payload.response.MessageResponse;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.security.jwt.JwtUtils;
import com.jjjteam.jmarket.security.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;




@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @Autowired
//    RefreshTokenService refreshTokenService;

    @Transactional
    public Boolean existsByUserId(String userid){



        return true;
    }


//    public void registerUser(@Valid SignupRequest signUpRequest){
//
//        log.info("현재클래스{}, 현재 메소드{}", Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
//
//        if (userRepository.(signUpRequest.getUserid())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//        }
//        log.info("{}@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@", signUpRequest.getUserid());
//        // Create new user's account
////        User user = new User(signUpRequest.getUserid(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
//        User user = User.builder()
//                .userId(signUpRequest.getUserid())
//                .email(signUpRequest.getEmail())
//                .password(encoder.encode(signUpRequest.getPassword()))
//                .build();
//        Set<String> strRoles = signUpRequest.getRole();
//
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            log.info("1@@@@");
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            log.info("2@@@@");
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
}
