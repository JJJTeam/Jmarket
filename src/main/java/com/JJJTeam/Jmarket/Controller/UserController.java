package com.JJJTeam.Jmarket.Controller;


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
	
	//회원가입
	@GetMapping("/login/loginFrom")
	public String loginFrorm() {
		return "login/login_form";
	}
	
	//로그인
	@GetMapping("/login/login")
	public String loginUser() {
		return "login/login";
	}
	
	//회원가입 시 DB에 저장 후 메인화면으로 화면호출
	@PostMapping("/UserFrom")
	public String userAdd(UserDTO userDTO) {
		userService.joinUser(userDTO);
		return "redirect:/";
	}
	
	//메인화면 로그인 시 user페이지로 이동?
	@PostMapping("/user")
	public String userLogin(UserDTO userDTO) {
		userService.selectUser(userDTO);
		return "redirect:/user";
	}

}
