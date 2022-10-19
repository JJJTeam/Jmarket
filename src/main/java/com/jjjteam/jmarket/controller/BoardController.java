package com.jjjteam.jmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController{
	
	@RequestMapping("/list")
	public String board() {
		return "list";
	}
	
} 

