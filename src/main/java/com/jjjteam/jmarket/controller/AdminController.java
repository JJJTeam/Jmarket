package com.jjjteam.jmarket.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
@Controller
public class AdminController {

	@GetMapping("/index")
	public String ToMyPageAddressList() {
		return "/admin/index";
	}


}
