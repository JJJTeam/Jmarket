package com.jjjteam.jmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public void index() {
		System.out.println("index");
		return "index";
	}
}
