package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email); // 회원가입 할 때 중복인지 확인하려고
}
