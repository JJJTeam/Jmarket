package com.JJJTeam.Jmarket;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.JJJTeam.Jmarket.dto.MemberDTO;
import com.JJJTeam.Jmarket.service.MemberService;

@Controller
public class MemberController {

	
	MemberService service;
	
	//회원가입 get
	@RequestMapping(value="/member/join", method =RequestMethod.GET)
	public void getRegister() throws Exception{
	
	}
	
	//회원가입 Post
	@RequestMapping(value="/member/join", method = RequestMethod.POST)
	public String postRegister(MemberDTO dto) throws Exception{
		
		service.join(dto);
		return null;
	}
}
