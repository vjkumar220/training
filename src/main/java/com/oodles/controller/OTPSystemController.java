package com.oodles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.OTPSystem;
import com.oodles.services.OTPService;

@RestController
public class OTPSystemController {
	@Autowired
	OTPService otpService;
	@RequestMapping(value="/mobilenumbers/{mobilenumber}/otp",method=RequestMethod.POST)
	public ResponseEntity<Object>sendOTP(@PathVariable String mobilenumber) 
	{
		ResponseEntity<Object> result = otpService.sendOTP(mobilenumber);

		return result;

	}
	@RequestMapping(value="/mobilenumbers/{mobilenumber}/otp",method=RequestMethod.PUT)
	public ResponseEntity<Object> verifyOTP(@PathVariable String mobilenumber,@RequestBody OTPSystem requestBodyOTPSystem)
	{
		ResponseEntity<Object> result = otpService.verifyOtp(mobilenumber, requestBodyOTPSystem);



		return result;
	}
	}