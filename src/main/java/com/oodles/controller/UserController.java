package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.User;
import com.oodles.dto.EmailDto;
import com.oodles.dto.EmailVerifyDto;
import com.oodles.dto.OtpDto;
import com.oodles.dto.UserDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.UserService;
import com.oodles.util.ResponseHandler;;

@RestController
/*@RequestMapping("/v1")*/
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
	public Map<String, Object> createUser(@Valid @RequestBody UserDto user) {
		Map<Object, Object> output = userService.createUser(user);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, output);
	}

	/**
	 * getting all user
	 * 
	 * @return
	 */
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/admin/users")
	public Map<String, Object> viewAllUsers() {
		List<User> output = userService.retrieveAllUser();
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, output);
	}

	/**
	 * getting all user by id
	 * 
	 * @param id
	 * @return
	 */
	//@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/user/{userId}")
	public Map<String, Object> findUserById(@PathVariable String userId) {
		Map result = userService.findUserById(userId);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	/**
	 * deleting user by id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/user/{userId}")
	public Map<String, Object> deleteUser(@PathVariable String userId) {
		Map<Object, Object> output = userService.deleteUser(userId);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, output);

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
/*	@PutMapping(value = "/update/user/feild/{id}/{name}/{email}/{password}/{phoneNumber}/{country}")
	public Map<String, Object> updateUser(@RequestParam String id, @RequestParam(required = false) String name,
			@RequestParam(required = false) @Email String email, @RequestParam(required = false) String password,
			@RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String country) {
		User user = userService.updateUser(id, name, email, password, phoneNumber, country);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, user);
	}*/
	@PutMapping(value = "/user/feild/{userId}/{name}/{email}/{password}/{phoneNumber}/{country}")
	public Map<String, Object> updateUser(@RequestParam String userId, @RequestParam String name,
			@RequestParam @Email String email, @RequestParam String password,
			@RequestParam String phoneNumber, @RequestParam String country) {
		Map<String,String> user = userService.updateUser(userId, name, email, password, phoneNumber, country);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, user);
	}

	/**
	 * verify the user
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping(value = "user/send/otp/{userId}")
	public Map<String, Object> sendOtp(@PathVariable Long userId) {
		String result = userService.sendOTP(userId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	@PutMapping(value = "user/veify/otp/")
	public Map<String, Object> verifyOtp(@RequestBody OtpDto requestOTP) {
		String result = userService.verifyOtp(requestOTP);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	/**
	 * Sending Mail
	 * 
	 * @param userId
	 * @return
	 */

	@PostMapping(value = "user/send/verification/mail/{userId}")
	public Map<String, Object> sendMail(@PathVariable Long userId) {
		logger.info("Mail controller send mail");
		String result = userService.sendMail(userId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	/**
	 * Verify Email
	 * 
	 * @param emailAddress
	 * @param verifyEmail
	 * @return
	 */
	@PutMapping(value = "user/verify/mail/{emailAddress}/verification")
	public Map<String, Object> verifyMail(@PathVariable String emailAddress, @RequestBody EmailDto verifyEmail) {
		String result = userService.verifyEmail(emailAddress, verifyEmail);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	// Sending Mail for Reset password
	@PostMapping(value = "user/verification/mail/forget/password/userId/{userId}")
	public Map<String, Object> forgetPasswordMail(@PathVariable Long userId) {

		String result = userService.forgetPassword(userId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	/**
	 * 
	 * @param emailAddress
	 * @param verifyEmail
	 * @return
	 */
	@PutMapping(value = "/user/emailAddress/{emailAddress}/password")
	public Map<String, Object> updatePassword(@PathVariable String emailAddress,
			@RequestBody EmailVerifyDto verifyEmail) {
		String result = null;
		try {
			result = userService.verifyEmailAndUpdatePass(emailAddress, verifyEmail);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

}
