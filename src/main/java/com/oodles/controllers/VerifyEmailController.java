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

import com.oodles.dto.VerifyEmailDto;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.VerifyEmailService;

@RestController
public class VerifyEmailController {
	private static final Logger logger = LoggerFactory.getLogger (VerifyEmailController.class);
@Autowired
private VerifyEmailService verifyemailService;
/**
 *  Sending Mail
 * @param userId
 * @return
 */

@PostMapping(value = "v1/verify/sendmail/{userId}")
public Map<String, Object> sendMail(@PathVariable String userId) {
	logger.info("Mail controller send mail");
	String result = null;
	try {
		result = verifyemailService.sendMail(userId);
		logger.info("send controller try block");
		return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
	} catch (ResourceNotFoundException exception) {
		logger.info("send controller catch block RNS");
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Email id does not exist", null, result);
	} catch (NoSuchElementException excep) {
		logger.info("send controller catch block NSE");
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	}
}

/**
 *  Verify Email
 * @param emailAddress
 * @param verifyEmail
 * @return
 */
@PutMapping(value = "v1/verifymail/{emailAddress}")
public Map<String, Object> verifyMail(@PathVariable String emailAddress,@RequestBody VerifyEmailDto verifyEmail) {
	logger.info("Mail controller verify mail start");
	String result = null;
	try {
		result = verifyemailService.verifyEmail(emailAddress,verifyEmail);
		logger.info("Mail verify controller end");
		return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
	} catch (ResourceNotFoundException exception) {
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Mail id does not exist", null, result);
	} catch (NoSuchElementException excep) {
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Wrong mail id", null, result);
	}
}
}
