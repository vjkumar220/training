package com.oodles.controllers;

import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.EmailPasswordDto;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.ForgotPasswordService;

@RestController
public class ChangePasswordController {
	private static final Logger logger = LoggerFactory.getLogger (VerifyEmailController.class);
	@Autowired
	private ForgotPasswordService verifyemailService;
	/**
	 *  Sending Mail
	 * @param id
	 * @return
	 */

	@PostMapping(value = "v1/updatepassword/sendmail/{id}")
	public Map<String, Object> sendMail(@PathVariable String id) {
		logger.info("Mail controller send mail");
		String result = null;
		
			result = verifyemailService.sendMail(id);
			logger.info("send controller try block");
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
	}

	/**
	 *  Change Password
	 * @param verifyEmail
	 * @return
	 */
	@PutMapping(value = "v1/updatepassword/{newpassword}")
	
	public Map<String, Object> forgotPassword(@RequestBody EmailPasswordDto verifyEmail) {
		logger.info("Mail controller verify mail start");
		String result = null;
		
			result = verifyemailService.forgotPassword(verifyEmail);
			logger.info("Mail verify controller end");
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
	}
}