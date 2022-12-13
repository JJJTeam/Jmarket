package com.jjjteam.jmarket.test;


import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class TestControllerService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;
    private final UserAddressRepository userAddressRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Value("${itemdtail}")
    String itemdtail;
    @Value("${repimg}")
    String repimg;

    public void addTestUser(){
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
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
        ItemFormDTO itemFormDTO = new ItemFormDTO();
        itemFormDTO.setItemDetail(itemdtail);
        itemFormDTO.setItemIntroduction("울 혼방 소재 코트. 긴소매 와이드 라펠 칼라 디자인. 같은 소재 리본 스타일 벨트. 배색된 핀스트라이프 디테일.");
        itemFormDTO.setItemNm("핀스트라이프 코트 LIMITED EDITION");
        itemFormDTO.setItemSellStatus(ItemSellStatus.SELL);
        itemFormDTO.setPrice(299000);
        itemFormDTO.setRepimg(repimg);
        itemFormDTO.setStockNumber(432);
        for (int i=0; i<100;i++){
            Item item = itemFormDTO.setRepTime().createItem();
            itemRepository.save(item);
        }
    }



}
