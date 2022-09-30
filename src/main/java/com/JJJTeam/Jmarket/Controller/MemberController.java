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
	
	private MemberService userservice;
	
	@Autowired
	public MemberController(MemberService userService) {
		this.userservice = userService;
	}
	
	@GetMapping("/member/create")
	public String create() {
		return "/members/memberForm";
	}
	
	@PostMapping("/member/create")
	public String create(MemberDTO userDTO) {
		Member member = new Member(null);
		member.toString();
		
		System.out.println("member = " + member.toString());
		
		userservice.join(member);
		 
		return "/members/memberForm";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}

}
