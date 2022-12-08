package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.constant.ValidText;
import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.util.Naver_Sens_V2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public List<String> DoubleCheckTextList(UserDTO userDTO, boolean phoneAuth, String phoneNumberAuth){
        String checkIdMessege = ValidText.getValidText("checkId",userRepository.existsByUserId(userDTO.getUserid()));
        String checkEmailMessege = ValidText.getValidText("checkEmail",userRepository.existsByEmail(userDTO.getEmail()));
        String phoneAuthMessege = ValidText.getValidText("phoneAuth",phoneAuth&& userDTO.getUserPhoneNumber().equals(phoneNumberAuth));
        List<String> messege = Arrays.asList(checkIdMessege,checkEmailMessege,phoneAuthMessege);
        List<String>resultMessege = messege.stream().filter(i -> !i.equals("pass")).collect(Collectors.toList());
        return resultMessege;
    }
    @Transactional
    public String PhoneCheck(UserDTO userDTO, boolean phoneAuth, String phoneNumberAuth){
        String phoneAuthMessege = ValidText.getValidText("phoneAuth",phoneAuth&& userDTO.getUserPhoneNumber().equals(phoneNumberAuth));
        return phoneAuthMessege;
    }

    @Transactional
    public void registerUser(UserDTO userDTO) {
        userRepository.save(userDTO.setRole(returnRoleUserSet()).setPassword(passwordEncoder.encode(userDTO.getPassword())).toEntity());
    }
    @Transactional
    public Set<Role> returnRoleUserSet(){
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        return roles;
    }
    @Transactional
    public Set<Role> returnRoleAdminSet(){
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        return roles;
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
    public User returnUserDetailById(Long id){
        return userRepository.findById(id).get();
    }
    public void changeUserEmail(Long id, String newEmail){
        User user = userRepository.findById(id).get();
        user.setEmail(newEmail);
        userRepository.save(user);
    }
    public void changeUserPhoneNumber(Long id, String phoneNumber){
        User user = userRepository.findById(id).get();
        user.setUserPhoneNumber(phoneNumber);
        userRepository.save(user);
    }
    public void changeUserPassword(Long id, String password){
        User user = userRepository.findById(id).get();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


}
