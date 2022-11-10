package com.jjjteam.jmarket.controller;



import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.dto.payload.request.SignUpRequest;
import com.jjjteam.jmarket.dto.payload.response.MessageResponse;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserAddressService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor

@Slf4j
public class MemberController {

	private final UserService userService;
	private final UserAddressService userAddressService;
	private final UserRepository userRepository;
	private final UserAddressRepository userAddressRepository;

	@Secured("ROLE_USER")
	@GetMapping("/member/mypageAddress")
	public String ToMyPageAddressList(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/member/mypageAddress";
	}
	@PostMapping("/signup")
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
	@GetMapping("/api/checkId")
	@ResponseBody
	public boolean checkId(@RequestParam(value="userId") String userId)	{
		return userService.existsByUserId(userId);
	}
	@GetMapping("/api/checkEmail")
	@ResponseBody
	public boolean checkEmail(@RequestParam(value="email") String email){
		return userService.existsByEmail(email);
	}
	@PostMapping("/api/phoneAuth")
	@ResponseBody
	public Boolean phoneAuth(@RequestBody String userPhoneNumber,HttpSession session) {
		try { // 이미 가입된 전화번호가 있으면
			if(userService.memberTelCount(userPhoneNumber))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String code = userService.sendRandomMessage(userPhoneNumber);
		session.setAttribute("rand", code);
		return false;
	}

	@PostMapping("/api/phoneAuthOk")
	@ResponseBody
	public Boolean phoneAuthOk(HttpSession session, HttpServletRequest request) {



		String rand = (String) session.getAttribute("rand");
		String code = (String) request.getParameter("code");

		System.out.println(rand + " : " + code);

		if (rand.equals(code)) {
			session.removeAttribute("rand");
			return false;
		}

		return true;
	}
}
