package com.oodles.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.User;
import com.oodles.service.UserService;

@RestController
@RequestMapping("v1/")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping(value = "/signup")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		ResponseEntity<Object> result = userService.createUser(user);

		return result;
	}

	@GetMapping(value = "/users")
	public List<User> viewAllUsers() {

	}
}
