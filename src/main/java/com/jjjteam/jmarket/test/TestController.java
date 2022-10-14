package com.jjjteam.jmarket.test;


import com.jjjteam.jmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
	
	@GetMapping("/test")
	public String toTeste() {
		return "/test";
	}
	

}
