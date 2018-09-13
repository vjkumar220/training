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
		verifyEmail.setExpirytime(System.currentTimeMillis() + 200000);
		verifyEmail.setConfirmationToken(otpCode);
		emailData.put(emailTo, verifyEmail);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shubhamsinghgu@gmail.com");
		mail.setTo(emailTo);
		mail.setSubject("Please verify your OTP");
		mail.setText(body);
		user.setConfirmationToken(otpCode);
		userRepository.save(user);
		id = userId;
		javaMailSender.send(mail)
;
		return "Your verification code has been sent to your mail";
	}

	// Verify Email
	public String verifyEmail(String emailAddress, VerifyEmailDto verifyEmail) {
		if (verifyEmail.getConfirmationToken()== null || verifyEmail.getConfirmationToken().trim().length() <= 0) {
			return "Please provide Verification code";
		}
		if (emailData.containsKey(emailAddress)) {
			VerifyEmailDto emailDto = emailData.get(emailAddress);
			if (emailDto != null) {
				if (emailDto.getExpirytime() >= System.currentTimeMillis()) {
					if (verifyEmail.getConfirmationToken().equals(emailDto.getConfirmationToken())) {
						Optional<User> foundUser = userRepository.findById(Long.parseLong(id));
						User user = foundUser.get();
						user.setEnabled(true);
						userRepository.save(user);
						emailData.remove(emailAddress);
						return "Verificarion code is verified successfully";
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
