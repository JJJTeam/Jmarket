package com.JJJTeam.Jmarket.Controller;

<<<<<<< HEAD
public class UserController {

=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/user/login")
	public String login() {
		return "login";
	}
>>>>>>> testJTB
}
