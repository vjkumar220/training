package com.oodles.controllers;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.UserDto;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.models.User;
import com.oodles.services.UserService;

@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;

	/**
	 *  Create
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/signup")

	public Map createUser(@Valid @RequestBody UserDto user) {
		Map result = null;
		
			result = userService.createUser(user);
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, "success", null, result);
	}

	/**
	 *  To Retrieve all detail
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/v1/admin/users")
	
	public List<User> viewAllUsers() {
		
		return userService.retrieveAllUser();
	}

	/**
	 *  To retrieve user by id
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/v1/admin/users/{userid}")
	public Map getUser(@PathVariable(value = "userid") String id) {
		Map result = null;

		
			result = userService.retriveUser(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
	}

	/**
	 *  To update the particular user
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param mobilenumber
	 * @param country
	 * @return
	 */

	@RequestMapping(method = RequestMethod.PUT, value = "/v1/user/users/{id}/{name}/{email}/{password}/{mobilenumber}/{country}")
	@ResponseBody
	public Map updateUser(@PathVariable String id, @PathVariable String name, @PathVariable String email,
			@PathVariable String password, @PathVariable String mobilenumber, @PathVariable String country) {
		User user = null;
		
			user = userService.updateUser(id, name, email, password, mobilenumber, country);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, user);
		
	}

	/**
	 *  Delete a user
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/v1/admin/users/{id}")
	@ResponseBody
	public Map deleteUser(@PathVariable String id) {
		Map result = null;

		
			result = userService.deleteUser(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
	}
}
