package com.basic.todos.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todos.models.AuthenticationBean;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
public class UserAuthencticationController {

	@GetMapping("/basicauth")
	public AuthenticationBean helloworldbean() {
		return new AuthenticationBean("You are authenticated");
	}
}
