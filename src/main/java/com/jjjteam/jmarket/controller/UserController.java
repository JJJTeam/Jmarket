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

	@GetMapping("/modify")
	public String ToMyPageInfo(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/mypage/modify";
	}
//	@PostMapping("/info")
//	public String CheckPassInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, String password,Model model){
//		if(encoder.matches(password, userDetails.getPassword())){return "/mypage/modify";}
//		else {return "/mypage/passerror";}
//	}
	@GetMapping("/change-email")
	public String ToChangeEmailPage(@AuthenticationPrincipal UserDetailsImpl userDetails,Model model) {
		model.addAttribute("user",userService.returnUserDetailById(userDetails.getId()));
		return "/mypage/change-email";
	}
	@PostMapping("/change-email")
	public String ToChangeEmailProcess(@AuthenticationPrincipal UserDetailsImpl userDetails,String newEmail, String inputPassword) {
		if(encoder.matches(inputPassword, userDetails.getPassword())){
			userService.changeUserEmail(userDetails.getId(),newEmail);
			return "/index";
		} else {
			return "/mypage/passerror";
		}

	}
	@GetMapping("/change-password")
	public String ToChangePassword(Model model) {
		return "/mypage/change-password";
	}
	@GetMapping("/change-phone-number")
	public String ToChangePhoneNumber(Model model) {
		return "/mypage/change-phone-number";
	}
	@GetMapping("/delete-account")
	public String ToDeleteAccount(Model model) {
		return "/mypage/delete-account";
	}
}
