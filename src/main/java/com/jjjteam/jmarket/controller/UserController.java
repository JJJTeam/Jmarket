package com.jjjteam.jmarket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.service.UserService;



@Controller
public class UserController {
	
	@Autowired private UserService userService;
	
	@GetMapping("/role_anonymous/signInPage")
	public String toLoginPage() throws Exception {
		
		try {
			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "/role_anonymous/signInPage";
	}
	
	@GetMapping("/role_anonymous/signUpPage")
	public String toSignUpPage() throws Exception {
		
		try {
			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "/role_anonymous/signUpPage";
	}
	

}
