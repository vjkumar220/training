package com.student.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.domain.Otp;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OtpService {
	
	static Logger logger =LoggerFactory.getLogger(OtpService.class);
	
	private Map<String, Otp> otp_data = new HashMap<>();
	private final static String ACCOUNT_SID = "ACe2f058ec28715b76bdeaac2da52d1324";
	private final static String AUTH_ID = "66384b401747451a0cf47404f3ae8d4c" + "";
	static {

		logger.info("OTP service - static block");
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	public ResponseEntity<Object> sendOTP(String mobilenumber) {
		
		logger.info("OTP service - sendOTP");


		Otp otp = new Otp();
		otp.setMobileNumber(mobilenumber);
		otp.setOtp(String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000));
		otp.setExpirytime(System.currentTimeMillis() + 20000);
		otp_data.put(mobilenumber, otp);
		Message.creator(new PhoneNumber("+918700153661"), new PhoneNumber("+19852384430"),
				"Your OTP is" + "" + otp.getOtp()).create();

		logger.info("OTP service - sendOTP - result get");

		
		return new ResponseEntity<>("Yor OTP send successfully..!!", HttpStatus.OK);

	}

	public ResponseEntity<Object> verifyOtp(String mobilenumber, Otp requestOTP) {

		logger.info("OTP service - verifyOTP");

		
		if (requestOTP.getOtp() == null || requestOTP.getOtp().trim().length() <= 0) {

			return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);

		}
		if (otp_data.containsKey(mobilenumber)) {

			Otp otp = otp_data.get(mobilenumber);

			if (otp != null) {

				if (otp.getExpirytime() >= System.currentTimeMillis()) {

					if (requestOTP.getOtp().equals(otp.getOtp())) {

						otp_data.remove(mobilenumber);

						logger.info("OTP service - sendOTP - get the result");

						return new ResponseEntity<>("OTP is verified successfully", HttpStatus.OK);
					}
					return new ResponseEntity<>("OTP is invalid", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("OTP is expired", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Mobile number is not found", HttpStatus.BAD_REQUEST);
	}
}
