package com.oodles.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.User;
import com.oodles.dto.EmailDto;
import com.oodles.dto.EmailVerifyDto;
import com.oodles.dto.OtpDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.UserService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	/**
	 * creating user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/signup")
	public Map<String, Object> createUser(@Valid @RequestBody User user) {
		Map<Object, Object> output = new HashMap<Object, Object>();
		try {
			logger.info("UserController - create value in try");
			output = userService.createUser(user);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);
		} catch (ResourceNotFoundException e) {
			logger.info("UserController - create value in catch'");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);
		}
	}

	/**
	 * getting all user
	 * 
	 * @return
	 */
	@GetMapping(value = "/all/users")
	public List<User> viewAllUsers() {
		List<User> result = userService.retrieveAllUser();
		return result;
	}

	/**
	 * getting all user by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/users/by/id/{id}")
	public Map<String, Object> findUserById(@PathVariable String id) {
		Map<Object, Object> output = new HashMap<Object, Object>();
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

	/**
	 * deleting user by id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/users/by/userId/{id}")
	public Map<String, Object> deleteUser(@PathVariable String id) {
		Map<Object, Object> output = new HashMap<Object, Object>();
		try {
			output = userService.deleteUser(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);

		} catch (ResourceNotFoundException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		} catch (NoSuchElementException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		}

	}

	/**
	 * update user by id
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param phoneNumber
	 * @param country
	 * @return
	 */
	@PutMapping(value = "/update/user/feild/{id}/{name}/{email}/{password}/{phoneNumber}/{country}")
	public Map<String, Object> updateUser(@PathVariable String id, @PathVariable String name,
			@PathVariable String email, @PathVariable String password, @PathVariable String phoneNumber,
			@PathVariable String country) {
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

	/**
	 * verify the user
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping(value = "/send/otp/userId/{userId}")
	public Map<String, Object> sendOtp(@PathVariable String userId) {
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

	@PutMapping(value = "/veify/users/otp/mobileNumber/{mobileNumber}")
	public Map<String, Object> verifyOtp(@PathVariable String mobileNumber, @RequestBody OtpDto requestOTP) {
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

	/**
	 * Sending Mail
	 * 
	 * @param userId
	 * @return
	 */

	@PostMapping(value = "/send/verification/mail/to/user/{userId}")
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

	/**
	 * Verify Email
	 * 
	 * @param emailAddress
	 * @param verifyEmail
	 * @return
	 */
	@PutMapping(value = "/verify/mail/{emailAddress}/verification")
	public Map<String, Object> verifyMail(@PathVariable String emailAddress, @RequestBody EmailDto verifyEmail) {
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

	// Sending Mail for Reset password
	@PostMapping(value = "/verification/mail/forget/password/userId/{userId}")
	public Map<String, Object> forgetPasswordMail(@PathVariable String userId) {

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

	/**
	 * 
	 * @param emailAddress
	 * @param verifyEmail
	 * @return
	 */
	@PutMapping(value = "/emailAddress/{emailAddress}/update/password")
	public Map<String, Object> updatePassword(@PathVariable String emailAddress,
			@RequestBody EmailVerifyDto verifyEmail) {
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
