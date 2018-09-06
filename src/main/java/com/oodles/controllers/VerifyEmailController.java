package com.oodles.controllers;

import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.models.User;
import com.oodles.services.VerifyEmailService;

@Controller
public class VerifyEmailController {
	private static final Logger logger = LoggerFactory.getLogger (VerifyEmailController.class);
@Autowired
private VerifyEmailService verifyemailService;
// Sending Mail

@PostMapping(value = "signup/sendmail/{id}")
public Map<String, Object> sendMail(@PathVariable String id) {
	logger.info("Mail controller send mail");
	String result = null;
	try {
		result = verifyemailService.sendMail(id);
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

// Verify Email
@PutMapping(value = "signup/{email}/verification")
public Map<String, Object> verifyMail(@PathVariable String email, @RequestBody User verifyEmail) {
	logger.info("Mail controller verify mail start");
	String result = null;
	try {
		result = verifyemailService.verifyEmail(email, verifyEmail);
		logger.info("Mail verify controller end");
		return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
	} catch (ResourceNotFoundException exception) {
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	} catch (NoSuchElementException excep) {
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	}
}
}
