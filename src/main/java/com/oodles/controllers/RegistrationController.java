package com.oodles.controllers;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.models.User;
import com.oodles.services.UserService;

@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;

	// Create
	@RequestMapping(method = RequestMethod.POST, value = "/v1/signup")

	public Map createUser(@Valid @RequestBody User user) {
		Map result = null;
		try {
			result = userService.createUser(user);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (com.oodles.exceptions.ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	// To Retrieve all detail
	@GetMapping(value = "/users")
	public List<User> viewAllUsers() {
		List<User> result = userService.retrieveAllUser();
		return result;
	}

	// To retrieve user by id
	@RequestMapping(method = RequestMethod.GET, value = "/v1/users/{userid}")
	public Map getUser(@PathVariable(value = "userid") String id) {
		User result = null;

		try {
			result = userService.retriveUser(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, result);
		}
	}

	// To update the particular user

	@RequestMapping(method = RequestMethod.PUT, value = "/v1/users/{id}/{name}/{email}/{password}/{mobilenumber}/{country}")
	@ResponseBody
	public Map updateUser(@PathVariable String id, @PathVariable String name, @PathVariable String email,
			@PathVariable String password, @PathVariable String mobilenumber, @PathVariable String country) {
		User user = null;
		try {
			user = userService.updateUser(id, name, email, password, mobilenumber, country);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, user);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, user);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, user);
		}
	}

	// Delete a user
	@RequestMapping(method = RequestMethod.DELETE, value = "v1/users/{id}")
	@ResponseBody
	public Map deleteUser(@PathVariable String id) {
		User result = null;

		try {
			result = userService.deleteUser(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "Id does not exist", null, result);
		}
	}
}
