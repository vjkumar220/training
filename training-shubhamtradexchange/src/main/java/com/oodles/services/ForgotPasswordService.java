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

import com.oodles.dto.EmailPasswordDto;
import com.oodles.models.User;
import com.oodles.repository.UserRepository;

@Service
public class ForgotPasswordService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordService.class);
	private static JavaMailSender javaMailSender;
	String userid = null;

	@Autowired
	public ForgotPasswordService(JavaMailSender javaMailSender) {

		ForgotPasswordService.javaMailSender = javaMailSender;
	}

	private Map<String, User> emailData = new HashMap<>();

	/**
	 *  send mail
	 * @param id
	 * @return
	 */
	public String sendMail(String id) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		User user = value.get();
		String emailTo = user.getEmail();
		String passToken = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
		String body = "Your Verification OTP-" + passToken;
		// User verifyEmail = new User();
		user.setExpirytime(System.currentTimeMillis() + 200000);
		user.setPassToken(passToken);
		userRepository.save(user);
		emailData.put(emailTo, user);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shubhamsinghgu@gmail.com");
		mail.setTo(emailTo);
		mail.setSubject("Please check your new code");
		mail.setText(body);
		userid = id;
		javaMailSender.send(mail);
		return "Your code has been sent to your mail";
	}

	public String forgotPassword(EmailPasswordDto verifyEmail) {
		String password = verifyEmail.getPassword();
		logger.info("entered in verification block");
		if (verifyEmail.getPassToken() == null || verifyEmail.getPassToken().trim().length() <= 0) {
			return "Please provide Verification code";
		}
		String email = verifyEmail.getEmail();
		if (emailData.containsKey(email)) {
			User verifyEmails = emailData.get(email);
			if (verifyEmails != null) {
				if (verifyEmails.getExpirytime() >= System.currentTimeMillis()) {
					if (verifyEmail.getPassToken().equals(verifyEmails.getPassToken())) {
						Optional<User> foundUser = userRepository.findById(Long.parseLong(userid));
						User user = foundUser.get();
						user.setPassword(password);
						userRepository.save(user);
						emailData.remove(email);

					}
				}
			}
		}
		return "Your Password has changed  successfully";
	}
}
