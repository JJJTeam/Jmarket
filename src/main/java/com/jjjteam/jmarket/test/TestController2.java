package com.jjjteam.jmarket.test;
//package com.jjjteam.jmarket.test;
//
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class TestController {
////
////
////	private final UserService userService;
////
////	private final RoleRepository roleRepository;
////
////	private final TestControllerService testControllerService;
////
////	private final PasswordEncoder encoder;
////
////	private final UserRepository userRepository;
////
////	
////	@GetMapping("/addrole")
////	public String roleTest() {
////		roleRepository.save(new Role(ERole.ROLE_USER));
////		roleRepository.save(new Role(ERole.ROLE_MODERATOR));
////        roleRepository.save(new Role(ERole.ROLE_ADMIN));
////        return "index";
////	}
////	@GetMapping("/addadmin")
////	public String addAdmin() {
////		testControllerService.addAdmin();
////		return "index";
////	}
////	@GetMapping("/addtest")
////	public String toAddTestUser()  {
////		testControllerService.addTestUser();
////		return "index";
////	}
////
////	@GetMapping("/test")
////	public String totestPage()  {
////		return "test";
////	}
////
////	@PostMapping("/test")
////	public String registerUser(UserDTO userDTO, BindingResult bindingResult, Model model, HttpSession session) {
////		if(bindingResult.hasErrors()){return "test";}
////		userService.registerUser(userDTO);
////		return "/index";
////	}
////
////
//
//
//
//}
