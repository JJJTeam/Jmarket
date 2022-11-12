package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
