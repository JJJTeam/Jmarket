package com.JJJTeam.Jmarket.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
//	로그인 페이지로 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		logger.info("this page is Login page {}.", model);
		model.addAttribute("model", "LoginController model test");
		return "user/login";
	}
	
//	로그인 페이지에서 로그인 했을때 프로세스 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLoginProcess(Model model, String userId, String userPwd) {
		logger.info("로그인 프로세스 과정 {}.", model);
		logger.info("로그인 프로세스 과정 아이디 {}.", userId);
		logger.info("로그인 프로세스 과정 비밀번호{}.", userPwd);
		
		//세션 추가해야함
		model.addAttribute("UserId", userId);
		model.addAttribute("UserPwd", userPwd);
		model.addAttribute("model", "LoginController model test");
		
		return "user/login_test";
	}
}
