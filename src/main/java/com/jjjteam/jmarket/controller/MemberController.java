package com.jjjteam.jmarket.controller;



import com.jjjteam.jmarket.dto.payload.request.SignUpRequest;
import com.jjjteam.jmarket.dto.payload.response.MessageResponse;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserAddressService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MemberController {

	private final UserService userService;
	private final UserAddressService userAddressService;

	@GetMapping("/login")
	public String ToLoginPage() {
		return "/login";
	}

	@GetMapping("/signup")
	public String ToJoinPage() {
		return "/signup";
	}

	@GetMapping("/member/mypageAddress")
	@Secured("ROLE_USER")
	public String ToPageAddress(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddress> addressList = userAddressService.findByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/member/mypageAddress";
	}
	@PostMapping("/member/signup")
	public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
		if (userService.existsByUserId(signUpRequest.getUserid())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
		}
		userService.registerUser(signUpRequest);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
