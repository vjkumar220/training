package com.oodles.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oodles.domain.User;
import com.oodles.example.ResourceNotFound;
import com.oodles.example.UserNotFoundException;
import com.oodles.services.UsersService;


@Controller
public class UsersController {
	@Autowired
	private UsersService userService;	
	//Create 
@RequestMapping(method = RequestMethod.GET, value = "/rest/createuser/{name}")
	@ResponseBody
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

//Update
@RequestMapping(method = RequestMethod.GET, value = "/rest/updateuser/{id}/{name}")
@ResponseBody
public Map updateUser(@PathVariable String id ,@PathVariable String name)
{
User user=null;
try {
	user =userService.updateUser(id,name);
	return ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, user);
}
catch(UserNotFoundException exception){
	return ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, user);
}
catch(NoSuchElementException excep){
	return ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, user);
}
}
// Delete



//@DeleteMapping("/deleteusers/{id}")
@RequestMapping(method = RequestMethod.GET, value = "/deleteusers/{id}")
@ResponseBody
public Map deleteUser(@PathVariable String id)
{
	User result=null;
		
		try{
		 result=userService.deleteUser(id);
			return ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, result);
		}
		catch(UserNotFoundException exception){
			return ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
		catch(NoSuchElementException excep){
			return ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
}

}

