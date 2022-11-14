package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.constant.ValidText;
import com.jjjteam.jmarket.dto.payload.request.SignUpRequest;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.dto.payload.request.LoginRequest;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.util.Naver_Sens_V2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

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
    public List<String> DoubleCheckTextList(SignUpRequest signUpRequest,boolean phoneAuth, String phoneNumberAuth){
        String checkIdMessege = ValidText.getValidText("checkId",userRepository.existsByUserId(signUpRequest.getUserid()));
        String checkEmailMessege = ValidText.getValidText("checkEmail",userRepository.existsByEmail(signUpRequest.getEmail()));
        String phoneAuthMessege = ValidText.getValidText("phoneAuth",phoneAuth&&signUpRequest.getUserPhoneNumber().equals(phoneNumberAuth));
        List<String> messege = Arrays.asList(checkIdMessege,checkEmailMessege,phoneAuthMessege);
        List<String>resultMessege = messege.stream().filter(i -> !i.equals("pass")).collect(Collectors.toList());
        return resultMessege;
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
                .userBirthDate(signUpRequest.getUserBirthDate())
                .userName(signUpRequest.getUserName())
                .userSex(signUpRequest.getUserSex())
                .build();
        userRepository.save(user);
    }
    @Transactional
    public String sendRandomMessage(String tel) {
        Naver_Sens_V2 message = new Naver_Sens_V2();
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        System.out.println("회원가입 문자 인증 => " + numStr);

//        message.send_msg(tel, numStr);

        return numStr;
    }

    public boolean memberTelCount(String userPhoneNumber) {
        return userRepository.existsByUserPhoneNumber(userPhoneNumber);
    }
}
