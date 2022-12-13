package com.jjjteam.jmarket.test;


import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.constant.ValidText;
import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.util.Naver_Sens_V2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class TestControllerService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;
    private final UserAddressRepository userAddressRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public void addTestUser(){
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("roles : {}", roles);
        User user = User.builder()
                .userId("test")
                .email("test@test.com")
                .password(encoder.encode("test"))
                .roles(roles)
                .userBirthDate(LocalDate.now())
                .userName("유저이름")
                .userSex((byte) 1)
                .userPhoneNumber("010-3034-7578")
                .userRegisterDateTime(LocalDateTime.now())
                .build();
        userRepository.save(user);
        userAddressRepository.save(
                UserAddress.builder()
                        .address("서울 한강중심")
                        .addressDetail("403호")
                        .addressPhoneNumber("010-4343-7575")
                        .user(userRepository.getReferenceById(1L))
                        .person("우리집 고양이")
                        .postCode("1234")
                        .defaultAddress(true)
                        .build());
        userAddressRepository.save(
                UserAddress.builder()
                        .address("부산광역시 행복동 행복아파트")
                        .addressDetail("123403호")
                        .addressPhoneNumber("010-4343-1324")
                        .user(userRepository.getReferenceById(1L))
                        .person("개똥이")
                        .postCode("1234")
                        .defaultAddress(null)
                        .build());
        userAddressRepository.save(
                UserAddress.builder()
                        .address("주소테스트3")
                        .addressDetail("1203호")
                        .addressPhoneNumber("010-4123-1324")
                        .user(userRepository.getReferenceById(1L))
                        .person("하나무라")
                        .defaultAddress(null)
                        .postCode("1234")
                        .build());
        userAddressRepository.save(
                UserAddress.builder()
                        .address("주소테스트4")
                        .addressDetail("12호")
                        .addressPhoneNumber("010-413-1324")
                        .user(userRepository.getReferenceById(1L))
                        .person("하라")
                        .postCode("1234")
                        .defaultAddress(null)
                        .build());

    }

    public void addAdmin(){
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("roles : {}", roles);
        User user = User.builder()
                .userId("admin")
                .email("test@test.com")
                .password(encoder.encode("admin"))
                .roles(roles)
                .userName("운영자")
                .build();
        userRepository.save(user);
    }
    public void addItem() throws IOException, URISyntaxException {
        @Valid("${itemdtail}")
        String test = "";

//        ItemFormDTO itemFormDTO = new ItemFormDTO();
//        itemFormDTO.setItemDetail("");
//        Item item = itemFormDTO.setRepTime().createItem();
//        itemRepository.save(item);
    }



}
