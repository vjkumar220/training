package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.dto.OtpDto;
import com.oodles.models.User;
import com.oodles.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OTPService {
	@Autowired
	private UserRepository userRepository;
	public Map<String,OtpDto> otp_data=new HashMap<>();
	private String id=null;
	private final static String ACCOUNT_SID="ACe46a7e8c2e88ee736b1eece22bb8e74b";
	private final static String AUTH_ID="1e709fed396e810515968114c50e87e6";
	static
	{
		Twilio.init(ACCOUNT_SID,AUTH_ID);

	}
	public String sendOTP(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		User user = value.get();
		if (value.isPresent() && user.getMobilenumber() != null) {
			String otpCode = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
			OtpDto otp = new OtpDto();
			otp.setMobileNumber(user.getMobilenumber());
			otp.setOtp(String.valueOf(otpCode));
			//otp.setExpirytime(System.currentTimeMillis() + 500000);
			user.setExpirytime(System.currentTimeMillis() + 500000);
			user.setOtp(otpCode);
			userRepository.save(user);
			otp_data.put(user.getMobilenumber(),otp);
			id = userId;
			Message.creator(new PhoneNumber("+917985253313"),new PhoneNumber("+13203145689"),"Your OTP is"+otp.getOtp()).create();
			return "Your OTP send successfully..!!";
		}
		return "User not found";
	}

	/**
	 *  verify otp
	 * @param mobilenumber
	 * @param requestOTP
	 * @return
	 */
	public String verifyOtp( OtpDto requestOTP) {
		String mobilenumber=requestOTP.getMobileNumber();
		User mobileNumber = userRepository.findByMobilenumber(requestOTP.getMobileNumber());
		if (requestOTP.getOtp() == null || requestOTP.getOtp().trim().length() <= 0) {
			return "Please provide OTP";
		}
		if (otp_data.containsKey(mobilenumber)) {
			OtpDto otp = otp_data.get(mobilenumber);
			if (otp != null) {
				if (mobileNumber.getExpirytime() >= System.currentTimeMillis()) {
					if (requestOTP.getOtp().equals(otp.getOtp())) {
						Optional<User> foundUser = userRepository.findById(Long.parseLong(id));
						User user = foundUser.get();
						String emailCode = user.getConfirmationToken();
						String mobileCode = user.getOtp();
						if (emailCode != null && mobileCode != null) {
						user.setEnabled("Active");
						userRepository.save(user);
						return "OTP is verified successfully";
						}
					}
					return "OTP is invalid";
				}
				return "OTP is expired";
			}
			return "Something went wrong";
		}
		return "Mobile number is not found";
	}
	
}
	
	
	
	
	
	
	
