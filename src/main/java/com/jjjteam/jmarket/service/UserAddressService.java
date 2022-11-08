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
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;

    @Transactional
    public void updateUserAddress(Long id, Long userId){

    }

    @Transactional
    public List<UserAddressDTO> findByUserId(Long userID) {
        List<UserAddress> userAddresses = userAddressRepository.findByUserId(userID);
        return userAddresses.stream().map(UserAddressDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public void clearDefaultAddress() {
        UserAddress userAddress = userAddressRepository.findByDefaultAddress(true);
        userAddress.setDefaultAddress(null);
        userAddressRepository.save(userAddress);
    }
    @Transactional
    public void saveNewAddress(UserAddressDTO userAddressDTO, Boolean checkboxValue, Long userId) {
        userAddressRepository.save(
                UserAddress.builder()
                        .address(userAddressDTO.getAddress())
                        .addressDetail(userAddressDTO.getAddressDetail())
                        .person(userAddressDTO.getPerson())
                        .defaultAddress(checkboxValue)
                        .addressPhoneNumber(userAddressDTO.getAddressPhoneNumber())
                        .user(userRepository.findById(userId).get())
                        .postCode(userAddressDTO.getPostCode())
                        .build());
    }
}
