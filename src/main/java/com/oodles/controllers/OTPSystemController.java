package com.oodles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.models.User;
import com.oodles.services.OTPService;

@RestController
public class OTPSystemController {
	@Autowired
	private OTPService otpService;
	@RequestMapping(value="/v1/verifyUser/{id}",method=RequestMethod.POST)
	public ResponseEntity<Object>sendOTP(@PathVariable Long id) 
	{
		ResponseEntity<Object> result = otpService.sendOTP(id);

		return result;

	}
	@RequestMapping(value="/v1/verifyUser/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Object> verifyOTP(@PathVariable Long id,@RequestBody User requestBodyOTPSystem)
	{
		ResponseEntity<Object> result = otpService.verifyOtp(id, requestBodyOTPSystem);



		return result;
	}
	}