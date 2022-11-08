package com.jjjteam.jmarket.repository;



import com.jjjteam.jmarket.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUserId(Long userId);

    UserAddress findByDefaultAddress(Boolean checkboxValue);

    UserAddress findByUserIdAndId(Long userId, Long Id);


}
