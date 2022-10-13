package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//ibatis/MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(String userId);
}
