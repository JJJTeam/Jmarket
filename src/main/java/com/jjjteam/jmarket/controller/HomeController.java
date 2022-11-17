package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/login")
	public String ToLoginPage() {
		return "/login";
	}

	@GetMapping("/signup")
	public String ToJoinPage(UserDTO userDTO) {
		return "signup";
	}

	@GetMapping("/")
	public String toLoginPage() {
		return "index";
	}

	@GetMapping("/index")
	public String toLoginPage2()  {
		return "index";
	}

}
