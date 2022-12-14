package com.jjjteam.jmarket.controller;


import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserAddressService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;



@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
@Secured("ROLE_USER")
@Controller
public class UserController {

	private final UserService userService;
	private final UserAddressService userAddressService;

	private final PasswordEncoder encoder;

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
	@GetMapping("/change-email")
	public String ToChangeEmailPage(@AuthenticationPrincipal UserDetailsImpl userDetails,Model model) {
		model.addAttribute("user",userService.returnUserDetailById(userDetails.getId()));
		return "/mypage/change-email";
	}
	@PostMapping("/change-email")
	public String ToChangeEmailProcess(@AuthenticationPrincipal UserDetailsImpl userDetails,@Valid UserDTO userDTO, BindingResult bindingResult) {

		if(encoder.matches(userDTO.getPassword(), userDetails.getPassword())){
			if(bindingResult.hasErrors()){return "index";}
			userService.changeUserEmail(userDetails.getId(),userDTO.getEmail());
			return "/index";
		} else {
			return "/mypage/passerror";
		}
	}

	@GetMapping("/change-password")
	public String ToChangePassword(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		model.addAttribute("user",userService.returnUserDetailById(userDetails.getId()));
		model.addAttribute("userDTO", userService.returnUserDetailById(userDetails.getId()));
		return "/mypage/change-password";
	}
	@PostMapping("/change-password")
	public String ToChangePasswordProcess(@AuthenticationPrincipal UserDetailsImpl userDetails,@Valid UserDTO userDTO,
										  BindingResult bindingResult, String nowpassword) {
		if (bindingResult.hasErrors()) {
			return "/mypage/change-password";
		}
		if (encoder.matches(nowpassword, userDetails.getPassword())){
			userService.changeUserPassword(userDetails.getId(),userDTO.getPassword());
		}

		return "/index";
	}
	
	
	
	@GetMapping("/change-phone-number")
	public String ToChangePhoneNumber(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		model.addAttribute("user",userService.returnUserDetailById(userDetails.getId()));
		return "/mypage/change-phone-number";
	}
	@PostMapping("/change-phone-number")
	public String ToChangePhoneNumberProcess(@AuthenticationPrincipal UserDetailsImpl userDetails, UserDTO userDTO,HttpSession session) {
		String phoneAuth = userService.PhoneCheck(userDTO,(boolean) session.getAttribute("phoneAuth"),
				(String) session.getAttribute("phoneNumberAuth"));
		if (phoneAuth.equals("pass") && encoder.matches(userDTO.getPassword(), userDetails.getPassword())){
			log.info("TEST@@");
			userService.changeUserPhoneNumber(userDetails.getId(),(String) session.getAttribute("phoneNumberAuth"));
		} else {
			return "/mypage/passerror";
		}
		session.removeAttribute("phoneAuth");
		session.removeAttribute("phoneNumberAuth");
		return "/index";
	}
	@GetMapping("/delete-account")
	public String ToDeleteAccount(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		model.addAttribute("user",userService.returnUserDetailById(userDetails.getId()));
		return "/mypage/delete-account";
	}
	@PostMapping("/delete-account")
	public String ToDeleteAccountProcess(@AuthenticationPrincipal UserDetailsImpl userDetails,String password) {
		if (encoder.matches(password, userDetails.getPassword())){
			userService.deleteUserById(userDetails.getId());
		}
		return "/index";
	}
}
