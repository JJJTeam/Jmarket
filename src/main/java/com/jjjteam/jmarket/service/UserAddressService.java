package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<UserAddress> findByUserId(Long userID) {
        return userAddressRepository.findByUserId(userID);
    }
    @Transactional
    public void addUserAddress(UserAddress userAddress){
        userAddressRepository.save(userAddress);

    }

    public void clearDefaultAddress() {
        UserAddress userAddress = userAddressRepository.findByDefaultAddress(true);
        userAddress.setDefaultAddress(null);
        userAddressRepository.save(userAddress);
    }
    public void saveNewAddress(UserAddressDTO userAddressDTO, Boolean checkboxValue, Long userId) {
        userAddressRepository.save(
                UserAddress.builder()
                        .address(userAddressDTO.getAddress())
                        .addressDetail(userAddressDTO.getAddressDetail())
                        .person(userAddressDTO.getPerson())
                        .defaultAddress(checkboxValue)
                        .addressPhoneNumber(userAddressDTO.getAddressPhoneNumber())
                        .user(userRepository.findById(userId).get())
                        .build());
    }
}
