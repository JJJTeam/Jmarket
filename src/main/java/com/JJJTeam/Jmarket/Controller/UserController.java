package com.JJJTeam.Jmarket.Controller;

import org.apache.ibatis.javassist.compiler.ast.Member;
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
	
	@GetMapping("/login_form")
	public String loginFrorm() {
		return "login";
	}
	
	
	@PostMapping("/memberFrom")
	public String memberAdd(UserDTO userDTO) {
		userService.joinUser(userDTO);
		return "redirect:/";
	}

}
