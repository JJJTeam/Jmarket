package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestController {

	@GetMapping("/test2")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String totestPage2(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails)  {
		model.addAttribute("model" , userDetails.getEmail());
		return "test2";
	}
	@GetMapping("/test")
	@Secured("ROLE_USER")
	public String totestPage()  {
		return "test";
	}

}
