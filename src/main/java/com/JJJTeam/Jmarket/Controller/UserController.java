package com.JJJTeam.Jmarket.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/user/signInPage")
	public String toLoginPage() throws Exception {
		
		try {
			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "/user/signInPage";
	}
	
	@GetMapping("/user/signUpPage")
	public String toSignUpPage() throws Exception {
		
		try {
			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "/user/signUpPage";
	}

}
