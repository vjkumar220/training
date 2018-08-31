package com.oodles.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		
	public Map createUser(@Valid @RequestBody User user)  {
		Map result=null;
		try{
			 result=userService.createUser(user);
			 return com.oodles.exceptions.ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, result);
		}
		catch(com.oodles.exceptions.UserNotFoundException exception){
			return com.oodles.exceptions.ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}		
}
	
}