/*package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.oodles.models.User;
import com.oodles.repository.UserRepository;

@Service
public class EmailService {
@Autowired
private UserRepository userRepository;
private static final Logger logger = LoggerFactory.getLogger(EmailService.class);	
private static JavaMailSender javaMailSender;

@Autowired
public EmailService(JavaMailSender javaMailSender) {
	
    EmailService.javaMailSender = javaMailSender;
}
	
	public Map<String,Object> sendMail(User user) 
	{
		Map<String, Object> result = new HashMap<String, Object>();
		User userMail = userRepository.findByEmail(user.getEmail());
		
		if(userMail==null)
		{
			
			 user.setConfirmationToken(UUID.randomUUID().toString());
			 
				
				String appUrl = request.getScheme() + "://" + request.getServerName();
				
		}	
		
	SimpleMailMessage mail = new SimpleMailMessage();

	mail.setFrom("shubhamsinghgu@gmail.com");
	mail.setTo(user.getEmail());
	mail.setSubject("Email Verification");
	mail.setText("To confirm your e-mail address, please click the link below:\n"+ appUrl + "/confirm?token=" + user.getConfirmationToken());
	
	logger.info("Sending...");
    javaMailSender.send(mail);
}return result
}

public Map<String,Object> verifyaMail(User user) 
{
	Map<String, Object> result = new HashMap<String, Object>();














}
*/