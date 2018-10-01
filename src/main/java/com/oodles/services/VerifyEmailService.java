package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.oodles.dto.VerifyEmailDto;
import com.oodles.models.User;
import com.oodles.repository.UserRepository;

@Service
public class VerifyEmailService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(VerifyEmailService.class);
	private static JavaMailSender javaMailSender;
	private String id=null;
	@Autowired
	public VerifyEmailService(JavaMailSender javaMailSender) {

		VerifyEmailService.javaMailSender = javaMailSender;
	}
   
	private Map<String, VerifyEmailDto> emailData = new HashMap<>();
	
	public String sendMail(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		User user = value.get();
		String emailTo = user.getEmail();
		String otpCode = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
		String body = "Your Verification OTP-" + otpCode;
		VerifyEmailDto verifyEmail = new VerifyEmailDto();
		verifyEmail.setEmail(emailTo);
		//verifyEmail.setExpirytime(System.currentTimeMillis() + 200000);
		verifyEmail.setConfirmationToken(otpCode);
		emailData.put(emailTo, verifyEmail);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shubhamsinghgu@gmail.com");
		mail.setTo(emailTo);
		mail.setSubject("Please verify your OTP");
		mail.setText(body);
		user.setConfirmationToken(otpCode);
		user.setExpirytime(System.currentTimeMillis() + 200000);
		userRepository.save(user);
		id = userId;
		javaMailSender.send(mail)
;
		return "Your verification code has been sent to your mail";
	}

	/**
	 *  Verify Email
	 * @param emailAddress
	 * @param verifyEmail
	 * @return
	 */
	public String verifyEmail(VerifyEmailDto verifyEmail) {
		if (verifyEmail.getConfirmationToken()== null || verifyEmail.getConfirmationToken().trim().length() <= 0) {
			return "Please provide Verification code";
		}
		String emailAddress=verifyEmail.getEmail();
		if (emailData.containsKey(emailAddress)) {
			VerifyEmailDto emailDto = emailData.get(emailAddress);
			Optional<User> foundUser = userRepository.findById(Long.parseLong(id));
			User user = foundUser.get();
			if (emailDto != null) {
				if (user.getExpirytime() >= System.currentTimeMillis()) {
					if (verifyEmail.getConfirmationToken().equals(emailDto.getConfirmationToken())) {
						String emailCode = user.getConfirmationToken();
						String mobileCode = user.getOtp();
						String active=user.getFirstEnabled();
						if(active.equalsIgnoreCase("Active"))
						{if (emailCode != null && mobileCode != null) {
						user.setEnabled("Active");
						userRepository.save(user);
						emailData.remove(emailAddress);
						return "Verificarion code is verified successfully";
						}}return "Do mobile verification";
						}
					return "Verfication code is invalid";
				}
				return "Verification code is expired";
			}
			return "Something went wrong";
		}
		return "Email Id is not found";
	}
	
	
}
