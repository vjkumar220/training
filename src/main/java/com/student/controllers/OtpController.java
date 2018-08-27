package com.student.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.domain.Otp;
import com.student.service.OtpService;

@RestController
public class OtpController {

	static Logger logger = LoggerFactory.getLogger(OtpController.class);

	@Autowired
	OtpService otpService;

	@RequestMapping(value = "sendotp/{mobilenumber}/otp", method = RequestMethod.POST)
	public ResponseEntity<Object> sendOTP(@PathVariable String mobilenumber) {
		
		logger.info("OTP controller - sendOTP");

		ResponseEntity<Object> result = otpService.sendOTP(mobilenumber);
		
		logger.info("OTP controller - sendOTP - get result");

		return result;

	}

	@RequestMapping(value = "/sendotp/{mobilenumber}/otp", method = RequestMethod.PUT)
	public ResponseEntity<Object> verifyOtp(@PathVariable String mobilenumber, @RequestBody Otp requestOTP) {

		logger.info("OTP controller - verifyOTP");
		
		ResponseEntity<Object> result = otpService.verifyOtp(mobilenumber, requestOTP);
		
		logger.info("OTP controller - verifyOTP - get result");
		
		return result;

	}
}
