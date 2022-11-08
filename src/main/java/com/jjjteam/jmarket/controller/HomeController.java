package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {



	@GetMapping("/")
	public String toLoginPage() throws Exception {

		try {

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "index";
	}

	@GetMapping("/index")
	public String toLoginPage2()  {
		return "index";
	}

}
