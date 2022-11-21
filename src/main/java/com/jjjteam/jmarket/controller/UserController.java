package com.jjjteam.jmarket.controller;



import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserAddressService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequiredArgsConstructor
@Slf4j
@RequestMapping("mypage")
@Secured("ROLE_USER")
@Controller
public class UserController {

	private final UserService userService;
	private final UserAddressService userAddressService;
	@Autowired
	PasswordEncoder encoder;



	@GetMapping("/address")
	public String ToMyPageAddressList(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/mypage/address";
	}

	@GetMapping("/info")
	public String ToMyPageInfo(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/mypage/info";
	}
	@PostMapping("/info")
	public String CheckPassInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, String password,Model model){
		if(encoder.matches(password, userDetails.getPassword())){
			userService.
			return "/mypage/modify";}
		else {return "/mypage/passerror";}
	}
}
