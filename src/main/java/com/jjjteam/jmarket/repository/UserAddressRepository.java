package com.jjjteam.jmarket.repository;



import com.jjjteam.jmarket.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUserId(Long userId);

    UserAddress findByDefaultAddress(Boolean checkboxValue);

    UserAddress findByUserIdAndId(Long userId, Long Id);

    UserAddress findByPostCodeAndAddressAndUserIdAndDefaultAddressAndId(String postCode, String address, Long userId, Boolean defaultAddress,Long id);




}
