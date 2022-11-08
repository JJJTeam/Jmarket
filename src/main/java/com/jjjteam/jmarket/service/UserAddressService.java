package com.jjjteam.jmarket.service;


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

    @Transactional
    public List<UserAddress> findByUserId(Long userID) {
        return userAddressRepository.findByUserId(userID);
    }
    @Transactional
    public void addUserAddress(UserAddress userAddress){
        userAddressRepository.save(userAddress);

    }


}
