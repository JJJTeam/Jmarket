package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    Boolean existsByUserId(String userId);

    Boolean existsByEmail(String email);
}
