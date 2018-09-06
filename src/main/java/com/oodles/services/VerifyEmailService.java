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

import com.oodles.models.User;
import com.oodles.repository.UserRepository;

@Service
public class VerifyEmailService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(VerifyEmailService.class);	
	private static JavaMailSender javaMailSender;

	@Autowired
	public VerifyEmailService(JavaMailSender javaMailSender) {
		
	    VerifyEmailService.javaMailSender = javaMailSender;
	}
	private Map<String, User> emailData = new HashMap<>();
	//send mail
	public String sendMail(String id) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		User user = value.get();
		String emailTo = user.getEmail();
		String confirmationToken = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
		String body = "Your Verification OTP-" + confirmationToken;
		//User verifyEmail = new User();
		user.setExpirytime(System.currentTimeMillis() + 200000);
		user.setConfirmationToken(confirmationToken);
		emailData.put(emailTo, user);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shubhamsinghgu@gmail.com");
		mail.setTo(emailTo);
		mail.setSubject("Please verify your OTP");
		mail.setText(body);
		javaMailSender.send(mail);
		return "Your verification code has been sent to your mail";
	}
	public String verifyEmail(String email , User verifyEmail) {
		logger.info("entered in verification block");
		if (verifyEmail.getOtp() == null || verifyEmail.getOtp().trim().length() <= 0) {
			return "Please provide Verification code";
		}
		if (emailData.containsKey(email)) {
			User verifyEmails = emailData.get(email);
			if (verifyEmails != null) {
				if (verifyEmails.getExpirytime() >= System.currentTimeMillis()) {
					if (verifyEmail.getOtp().equals(verifyEmails.getOtp())) {
						emailData.remove(email);
						logger.info("verified service end");
						return "Verificarion code is verified successfully";
					}
					return "Verfication code is invalid";
				}
				return "Verification code is expired";
			}
			return "Something went wrong";
		}
		return "Email Address is not found";
	}
}
