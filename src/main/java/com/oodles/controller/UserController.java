package com.oodles.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.user.User;
import com.oodles.dto.EmailDto;
import com.oodles.dto.EmailVerifyDto;
import com.oodles.dto.Otp;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.UserService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping("v1/")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	Map output = null;

	// creating user
	@PostMapping(value = "/signup")
	public Map createUser(@Valid @RequestBody User user) {
		logger.info("UserController-create value start");
		try {
			logger.info("UserController - create value in try");
			output = userService.createUser(user);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);
		} catch (ResourceNotFoundException e) {
			logger.info("UserController - create value in catch'");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);
		}
	}

	// getting all user
	@GetMapping(value = "/users")
	public List<User> viewAllUsers() {
		List<User> result = userService.retrieveAllUser();
		return result;
	}

	// getting all user by id
	@GetMapping(value = "/users/{id}")
	public Map<String, Object> findUserById(@PathVariable String id) {
		Optional<User> result = null;
		try {
			result = userService.findUserById(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);
		} catch (NoSuchElementException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		}

	}

	// deleting user by id
	@DeleteMapping(value = "/users/{id}")
	public Map deleteUser(@PathVariable String id) {
		User delete = null;
		try {
			delete = userService.deleteUser(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, delete);

		} catch (ResourceNotFoundException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		} catch (NoSuchElementException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		}

	}

	// update user by id
	@PutMapping(value = "/users/{id}/{name}/{email}/{password}/{phoneNumber}/{country}")
	public Map updateUser(@PathVariable String id, @PathVariable String name, @PathVariable String email,
			@PathVariable String password, @PathVariable String phoneNumber, @PathVariable String country) {
		User user = null;
		try {
			user = userService.updateUser(id, name, email, password, phoneNumber, country);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, user);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, user);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, user);
		}
	}

	// verify the user
	@PostMapping(value = "/users/{userId}")
	public Map sendOtp(@PathVariable String userId) {
		String result = null;
		try {
			result = userService.sendOTP(userId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	@RequestMapping(value = "/users/otp/{mobileNumber}", method = RequestMethod.PUT)
	public Map verifyOtp(@PathVariable String mobileNumber, @RequestBody Otp requestOTP) {
		String result = null;
		try {
			result = userService.verifyOtp(mobileNumber, requestOTP);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	// Sending Mail

	@PostMapping(value = "signup/sendmail/{userId}")
	public Map<String, Object> sendMail(@PathVariable String userId) {
		logger.info("Mail controller send mail");
		String result = null;
		try {
			result = userService.sendMail(userId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	// Verify Email
	@PutMapping(value = "signup/{emailAddress}/verification")
	public Map<String, Object> verifyMail(@PathVariable String emailAddress, @RequestBody EmailDto verifyEmail) {
		logger.info("Mail controller send mail");
		String result = null;
		try {
			result = userService.verifyEmail(emailAddress, verifyEmail);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
	//Sending Mail for Reset password
	@PostMapping(value = "/forgetPassword/{userId}")
	public Map  forgetPasswordMail(@PathVariable String userId) {
		
		String result = null;
		try {
			result = userService.forgetPassword(userId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
	@PutMapping(value = "signup/{emailAddress}/updatePassword")
	public Map<String, Object> updatePassword(@PathVariable String emailAddress, @RequestBody EmailVerifyDto verifyEmail) {
		logger.info("Mail controller send mail");
		String result = null;
		try {
			result = userService.verifyEmailAndUpdatePass(emailAddress, verifyEmail);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

}
