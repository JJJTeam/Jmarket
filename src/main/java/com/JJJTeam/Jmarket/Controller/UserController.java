package com.JJJTeam.Jmarket.Controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.JJJTeam.Jmarket.DTO.UserDTO;
import com.JJJTeam.Jmarket.Service.UserService;

@Controller

public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원가입화면 호출
	@GetMapping("/login/loginFrom")
	public String loginFrorm() {
		return "login/login_form";
	}
	
	//로그인화면 호출
	@GetMapping("/login/login")
	public String loginUser() {
		return "login/login";
	}
	
	//로그인화면 -> 회원가입 
	@GetMapping("/Login_form")
	public String goTologinForm() {
		return "login/login_form";
	}
	
	//회원가입 시 DB에 저장 후 로그인화면으로 화면호출
	@PostMapping("/UserFrom")
	public String userAdd(UserDTO userDTO) {
		userService.joinUser(userDTO);
		return "login/login";
	}
	
	//메인화면 로그인 시 user페이지로 이동?
	/*
	 * @PostMapping("/user") public String userLogin(UserDTO userDTO) {
	 * userService.selectUser(userDTO); return "redirect:/user"; }
	 */
	
	
	//관리자 페이지로 이동??
	
	@PostMapping("/user")
	public String getSelectUser(UserDTO userDTO, HttpSession session) throws Exception{
		userDTO = userService.getSelectUser(userDTO);
		if(userDTO != null) {
			session.setAttribute("user", userDTO);
		}
		return "redirect:/user";
	}

}
