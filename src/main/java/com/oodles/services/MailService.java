package com.oodles.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component("shubhamsinghgu@gmail.com")
@Service
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);	
	private static JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
    	
        MailService.javaMailSender = javaMailSender;
    }

	public void sendMail(String from, String to, String subject, String body) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
 
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		
		logger.info("Sending...");
        javaMailSender.send(mail);
    }
}