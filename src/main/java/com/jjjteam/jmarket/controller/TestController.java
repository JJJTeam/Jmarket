package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {


	private final UserService userService;
	
	
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	
	@GetMapping("/addrole")
	public String roleTest() {
		roleRepository.save(new Role(ERole.ROLE_USER));
		roleRepository.save(new Role(ERole.ROLE_MODERATOR));
        roleRepository.save(new Role(ERole.ROLE_ADMIN));
        return "index";
	}
	@GetMapping("/addadmin")
	public String addAdmin() {
		Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("roles : {}", roles);
        User user = User.builder()
                .userId("admin")
                .email("test@test.com")
                .password(encoder.encode("admin"))
                .roles(roles)
                .userName("운영자")
                .build();
        userRepository.save(user);

		return "index";
	}
	
	
	@GetMapping("/test2")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String totestPage2(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails)  {
		model.addAttribute("model" , userDetails.getEmail());
		return "test2";
	}
	@GetMapping("/test")
	public String totestPage()  {
		log.info(userService.returnRoleUserSet().toString());

		return "test";
	}

	@PostMapping("/test")
	public String registerUser(UserDTO userDTO, BindingResult bindingResult, Model model, HttpSession session) {
		if(bindingResult.hasErrors()){return "test";}
		userService.registerUser(userDTO);
		return "/index";
	}





}
