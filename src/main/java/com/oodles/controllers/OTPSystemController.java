package com.oodles.controllers;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.OtpDto;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.OTPService;

@RestController
public class OTPSystemController {
	@Autowired
	private OTPService otpService;
	@RequestMapping(value="/v1/user/mobile/verify/{userId}",method=RequestMethod.POST)
	public Map sendOtp(@PathVariable String userId) {
		String result = null;
		
			result = otpService.sendOTP(userId);
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, "success", null, result);
		
	}
	@RequestMapping(value="/v1/user/mobile/verify",method=RequestMethod.PUT)
	public Map verifyOtp(@RequestBody OtpDto requestOTP) {
		String result = null;
		
			result = otpService.verifyOtp(requestOTP);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
	}
	}