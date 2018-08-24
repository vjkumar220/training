/*package com.oodles.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oodles.model.OTPSystem;

@Controller
public class OTPSystemController {
private Map<String,OTPModel> otp_data=new HashMap<>();
private final static String ACCOUNT_SID="";
private final static String AUTH_ID="";
static
{
	Twilio.init(ACCOUNT_SID,AUTH_ID);
	
}
@RequestMapping(value="/mobilenumbers/{mobilenumber}/otp",method=RequestMethod.POST)
public ResponseEntity<Object>sendOTP=(@PathVariable("mobilenumber")String mobilenumber)
{
	OTPSystem otpSystem =new OTPSystem();
	otpSystem.setMobilenumber(mobilenumber);
	otpSystem.setOtp(String.valueOf(((int)(Math.random()*(10000-1000)))+1000));
	otpSystem.setExpirytime(System.currentTimeMillis()+20000);
	otp_data.put(mobilenumber,otpSystem);
	Message.creator(new PhoneNumber("+917985253313"),new PhoneNumber("+"),"Your OTP is"+otpSystem.getOtp()).create();
	return new ResponseEntity<>("OTP IS SENT SUCCESFULLY",HttpStatus.OK);
}
@RequestMapping(value="/mobilenumbers/{mobilenumber}/otp",method=RequestMethod.PUT)
public ResponseEntity<Object> verifyOTP(@PathVariable("mobilenumber")String mobilenumber,@RequestBody OTPSystem requestBodyOTPSystem)
{
	if(requestBodyOTPSystem.getOtp()==null||requestBodyOTPSystem.getOtp().trim().length()<=0)
	{
		return new ResponseEntity<>("PLEASE PROVIDE OTP",HttpStatus.BAD_REQUEST);
	}
	if(otp_data.containsKey(mobilenumber))
	{
		OTPSystem otpSystem=otp_data.get(mobilenumber);
		if(otpSystem!=null)
			if(otpSystem.getExpirytime()>=System.currentTimeMillis())
			{
				if( )
			}
	}
}
}
*/