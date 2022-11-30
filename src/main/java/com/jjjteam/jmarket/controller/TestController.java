package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.service.UserService;
import com.jjjteam.jmarket.test.TestControllerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {


	private final UserService userService;

	private final RoleRepository roleRepository;

	private final TestControllerService testControllerService;

	private final PasswordEncoder encoder;

	private final UserRepository userRepository;

	
	@GetMapping("/addrole")
	public String roleTest() {
		roleRepository.save(new Role(ERole.ROLE_USER));
		roleRepository.save(new Role(ERole.ROLE_MODERATOR));
        roleRepository.save(new Role(ERole.ROLE_ADMIN));
        return "index";
	}
	
	@GetMapping("/addadmin")
	public String addAdmin() {
		testControllerService.addAdmin();
		return "index";
	}
	@GetMapping("/addtest")
	public String toAddTestUser()  {
		testControllerService.addTestUser();
		return "index";
	}

	@GetMapping("/test")
	public String totestPage()  {
		return "test";
	}

	@PostMapping("/test")
	public String registerUser(UserDTO userDTO, BindingResult bindingResult, Model model, HttpSession session) {
		if(bindingResult.hasErrors()){return "test";}
		userService.registerUser(userDTO);
		return "/index";
	}
	
	
	
	
	


}
