package com.oodles.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.StringConstant;
import com.oodles.dto.VerifyEmailDto;
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


@RequestMapping(method = RequestMethod.POST, value = "v1/user/email/verify/{userId}")
public Map<String, Object> sendMail(@PathVariable String userId) {
	logger.info("Mail controller send mail");
	String result = null;
	
		result = verifyemailService.sendMail(userId);
		logger.info("send controller try block");
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false,StringConstant.Success, null, result);
	
}

/**
 *  Verify Email
 * @param emailAddress
 * @param verifyEmail
 * @return
 */


@RequestMapping(method = RequestMethod.PUT, value = "v1/user/email/verify")

public Map<String, Object> verifyMail(@RequestBody VerifyEmailDto verifyEmail) {
	logger.info("Mail controller verify mail start");
	String result = null;
	
		result = verifyemailService.verifyEmail(verifyEmail);
		logger.info("Mail verify controller end");
		return ResponseHandler.generateResponse(HttpStatus.OK, false, StringConstant.Success, null, result);
	
}
}
