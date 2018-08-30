package com.oodles.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.models.User;
import com.oodles.services.UserService;

@RestController
public class RegistrationController {
	@Autowired
		private UserService userService;	
		//Create 
	@RequestMapping(method = RequestMethod.POST, value = "/v1/signup")
		
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)  {
			
		ResponseEntity<Object> result=userService.createUser(user);
		return result;		
}
	
}