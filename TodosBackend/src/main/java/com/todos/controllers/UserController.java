package com.todos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todos.models.User;
import com.todos.models.UserDTO;
import com.todos.services.InjectDataService;
import com.todos.services.UserAuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(InjectDataService.class);

	@Autowired
	private UserAuthService userAuthService;

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public boolean registerUser(@RequestBody User user) {
		logger.info("user in registeruser method is " + user);
		try {
			userAuthService.registerUser(user);
			return true;
		} catch (Exception e) {
			logger.info("Error while registering user : ", e.getMessage());
		}
		return false;
	}

	@PostMapping(value = "signin", produces = "application/json")
	public UserDTO loginUser(@RequestBody User user) throws Exception {
		UserDTO curruser = userAuthService.loginUser(user);
		return curruser;
	}

}
