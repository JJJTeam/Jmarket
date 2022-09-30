package com.JJJTeam.Jmarket.Service;


import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JJJTeam.Jmarket.DTO.MemberDTO;
import com.JJJTeam.Jmarket.Mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	//객체 주입
	@Autowired
    MemberMapper memberMapper;
	
	// 트랜잭션 보장이 된 메소드로 설정
	@Transactional
	public void joinMember(MemberDTO memberDTO) {
		memberMapper.saveMember(memberDTO);
	}
	
}
