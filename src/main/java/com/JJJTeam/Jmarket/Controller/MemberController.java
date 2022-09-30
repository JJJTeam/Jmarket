package com.JJJTeam.Jmarket.Controller;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.JJJTeam.Jmarket.DTO.MemberDTO;
import com.JJJTeam.Jmarket.Service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/memberFrom")
	public String loginFrorm() {
		return "memberform";
	}
	
	
	@PostMapping("/memberFrom")
	public String memberAdd(MemberDTO memberDTO) {
		memberService.joinMember(memberDTO);
		return "redirect:/";
	}

}
