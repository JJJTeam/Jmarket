package com.jjjteam.jmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//	// 사이트?
//	@GetMapping("/item/itemForm")
//	public String item() throws Exception{
//		try {
//
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//		return "item/itemForm";
//	}
	

	@GetMapping("/")
	public String toLoginPage() throws Exception {

		try {

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "index";
	}

	@GetMapping("/index")
	public String toLoginPage2() throws Exception {

		try {

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "index";
	}

}
