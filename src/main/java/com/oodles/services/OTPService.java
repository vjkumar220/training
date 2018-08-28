package com.oodles.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oodles.domain.OTPSystem;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OTPService {
	public Map<String,OTPSystem> otp_data=new HashMap<>();
	private final static String ACCOUNT_SID="ACe46a7e8c2e88ee736b1eece22bb8e74b";
	private final static String AUTH_ID="1e709fed396e810515968114c50e87e6";
	static
	{
		Twilio.init(ACCOUNT_SID,AUTH_ID);

	}
	public ResponseEntity<Object> sendOTP(String mobilenumber) {
		OTPSystem otpSystem =new OTPSystem();
		otpSystem.setMobilenumber(mobilenumber);
		otpSystem.setOtp(String.valueOf((int)(Math.random()*(10000-1000))+1000));
		otpSystem.setExpirytime(System.currentTimeMillis()+20000);
		otp_data.put(mobilenumber,otpSystem);
		Message.creator(new PhoneNumber("+917985253313"),new PhoneNumber("+13203145689"),"Your OTP is"+otpSystem.getOtp()).create();
		return new ResponseEntity<>("OTP IS SENT SUCCESFULLY",HttpStatus.OK);
	}
	public ResponseEntity<Object> verifyOtp(String mobilenumber, OTPSystem requestBodyOTPSystem) {
		if(requestBodyOTPSystem.getOtp()==null||requestBodyOTPSystem.getOtp().trim().length()<=0)
		{
			return new ResponseEntity<>("PLEASE PROVIDE OTP",HttpStatus.BAD_REQUEST);
		}
		if(otp_data.containsKey(mobilenumber))
		{
			OTPSystem otpSystem=otp_data.get(mobilenumber);
			if(otpSystem!=null)
			{
				if(otpSystem.getExpirytime()>=System.currentTimeMillis())
				{
					if(requestBodyOTPSystem.getOtp().equals(otpSystem.getOtp()))
					{
						otp_data.remove(mobilenumber);
						return new ResponseEntity<>("OTP is verified successfully",HttpStatus.OK);
					}
					return new ResponseEntity<>("Invalid OTP",HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("OTP is expired",HttpStatus.BAD_REQUEST);	
			}
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Mobile number is not found",HttpStatus.NOT_FOUND);
	}
}
