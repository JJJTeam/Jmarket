package com.JJJTeam.Jmarket.Controller.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		logger.info("this page is Login page {}.", model);

		model.addAttribute("model", "LoginController model test");
		
		return "user/login";
	}
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
