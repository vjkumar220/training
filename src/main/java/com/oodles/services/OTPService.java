package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(OTPService.class);
	@Autowired
	private UserRepository userRepository;
	public Map<String,OtpDto> otp_data=new HashMap<>();
	//private String id=null;
	private final static String ACCOUNT_SID="ACe46a7e8c2e88ee736b1eece22bb8e74b";
	private final static String AUTH_ID="1e709fed396e810515968114c50e87e6";
	static
	{
		Twilio.init(ACCOUNT_SID,AUTH_ID);

	}
	public String sendOTP(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		if (value.isPresent()) {
			User user = value.get();
			if (user.getMobilenumber() != null) {
				logger.info("ab"+user.getMobilenumber());
				String otpCode = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
				long expiryTimeOfOtp = System.currentTimeMillis() + 500000;
				OtpDto otp = new OtpDto();
				otp.setMobileNumber(user.getMobilenumber());
				otp.setOtp(String.valueOf(otpCode));
				user.setOtp(otpCode);
				user.setExpirytime(expiryTimeOfOtp);
				userRepository.save(user);
				logger.info("ab"+user.getMobilenumber());
				otp_data.put(user.getMobilenumber(), otp);
			
				Message.creator(new PhoneNumber("+917985253313"), new PhoneNumber("+13203145689"),
						"Your OTP is" + "" + otp.getOtp()).create();
				return "Your OTP send successfully..!!";
			}
			return "Mobile number is not found";
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
		if (requestOTP.getOtp() == null || requestOTP.getOtp().trim().length() <= 0) {
			return "Please provide OTP";
		}
		User user = userRepository.findByMobilenumber(requestOTP.getMobileNumber());
		Long expiryTime = user.getExpirytime();
		String otpCode = user.getOtp();
		if (otp_data.containsKey(requestOTP.getMobileNumber())) {
			OtpDto otp = otp_data.get(requestOTP.getMobileNumber());
			if (otp != null) {
				if (user.getExpirytime() >= System.currentTimeMillis()) {
					if (requestOTP.getOtp().equals(otp.getOtp())) {
						Optional<User> value = userRepository.findById(user.getId());
						if (value.isPresent()) {
							User user1 = value.get();
							String emailCode = user1.getConfirmationToken();
							String mobileCode = user1.getOtp();
							if (emailCode != null && mobileCode != null) {
								user1.setEnabled("Active");
								userRepository.save(user);
								
							}
						}
						otp_data.remove(requestOTP.getMobileNumber());
						return "OTP is verified successfully";
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
	
	
	
	
	
	
	
