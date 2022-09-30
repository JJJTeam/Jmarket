package com.JJJTeam.Jmarket.Service;


import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JJJTeam.Jmarket.DTO.MemberDTO;

@Service
public class MemberService {
	
	MemberDTO memberDTO;
	private Member member;
	
	@Autowired
	public MemberService(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public void join(Member member) {
		this.member = member;
	}
	
}
