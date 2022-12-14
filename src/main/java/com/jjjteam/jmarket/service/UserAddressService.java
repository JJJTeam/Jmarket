package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserAddressService {
    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;
    @Transactional
    public void updateUserAddress(UserAddressDTO userAddressDTO, Long userId){
        UserAddress userAddress = userAddressRepository.findByIdAndUserIdAndPostCodeAndAddress(
                userAddressDTO.getId(),userId,userAddressDTO.getPostCode(),userAddressDTO.getAddress()
        ).get();
        if (null!=userAddress){
            if (userAddressDTO.getDefaultAddress()!=null){
                userAddress.setDefaultAddress(true);
            }
            userAddress.setAddressDetail(userAddressDTO.getAddressDetail());
            userAddress.setAddressPhoneNumber(userAddressDTO.getAddressPhoneNumber());
            userAddress.setPerson(userAddressDTO.getPerson());
            userAddressRepository.save(userAddress);
        }

    }
    @Transactional
    public UserAddressDTO loadAddressByUserAndId(Long id, Long userId){
        return new UserAddressDTO(userAddressRepository.findByUserIdAndId(userId,id));
    }

    @Transactional
    public List<UserAddressDTO> loadAddressListByUserId(Long userID) {
        List<UserAddress> userAddresses = userAddressRepository.findByUserId(userID);
        return userAddresses.stream().map(UserAddressDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public UserAddressDTO loadDefaultAddressByUserId(Long userid) {
        UserAddress userAddresses = userAddressRepository.findByUserIdAndDefaultAddress(userid,true);
        return new UserAddressDTO(userAddresses);
    }
    @Transactional
    public void clearDefaultAddress(Long userid) {
        UserAddress userAddress = userAddressRepository.findByUserIdAndDefaultAddress(userid,true);
        if (userAddress!=null){
            userAddress.setDefaultAddress(null);
            userAddressRepository.save(userAddress);
        }
    }
    @Transactional
    public void saveNewAddress(UserAddressDTO userAddressDTO, Long userId) {
        userAddressRepository.save(userAddressDTO.toEntity(userRepository.findById(userId).get()));
    }
    @Transactional
    public void dropUserAddress(Long id, Long userId) {
        userAddressRepository.deleteByIdAndUserId(id,userId);

    }
    @Transactional
    public void updateUserDefaultAddress(Long id, Long userId) {
        UserAddress userAddress = userAddressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(userAddress.getUser().getId().equals(userId)){
            userAddress.setDefaultAddress(true);
            userAddressRepository.save(userAddress);
        }
    }

}
