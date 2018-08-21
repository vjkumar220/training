package com.oodles.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oodles.example.ResourceNotFound;
import com.oodles.example.UserNotFoundException;
import com.oodles.services.UsersService;


@Controller
public class UsersController {
	@Autowired
	private UsersService userService;	
	
@RequestMapping(method = RequestMethod.GET, value = "/rest/createuser/{name}")
	
	public Map createUser(@PathVariable String name)  {
		 Map result=null;
		
		try{
		 result=userService.createUser(name);
			return ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, result);
		}
		catch(UserNotFoundException exception){
			return ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
}	
}

