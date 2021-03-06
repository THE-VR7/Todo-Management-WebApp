package com.todos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todos.models.HelloWorldBean;
import com.todos.services.InjectDataService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	private InjectDataService injectDataService;

	@GetMapping()
	public HelloWorldBean getHome() {
		return new HelloWorldBean("Hello ! Welcome to Home Page - ");
	}
	
	@GetMapping(path = "/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello ! Welcome to Home Page,  %s", name));
	}
	
	@GetMapping(path = "/adddata/{username}/{size}")
	public boolean insertDataIntoTodo(@PathVariable String username, @PathVariable int size)
	{
		return injectDataService.insertData(username,size);
	}
}
