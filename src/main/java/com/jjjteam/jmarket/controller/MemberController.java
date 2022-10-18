package com.jjjteam.jmarket.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/member")
public class MemberController {

	
	@GetMapping("/loginForm")
	public String ToLoginPage() throws Exception {
		return "/member/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String ToJoinPage() throws Exception {
		return "/member/joinForm";
	}
	

}
