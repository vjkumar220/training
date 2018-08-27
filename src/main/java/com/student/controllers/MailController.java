package com.student.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.service.MailService;

@RestController
public class MailController {
	
	Logger logger =LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	MailService mailService;

	@RequestMapping(value= "/sendmail" ,method= RequestMethod.GET)
	public String sendMail() {
		
	logger.info("Mail controller send mail");
		
		mailService.sendMail("shivam4848@gmail.com", "Shivam.Sahu@oodlestechnologies.com", "Test Subject", "Test Message");
		
		logger.info("Mail controller send mail successfully");
		return"Mail Sent Successfully..!!";
	}
	
	@RequestMapping(value ="/templatemail" , method=RequestMethod.GET)
	public String templateMail() {
		
		logger.info("Mail controller template mail");
		
		mailService.templateMail("shivam4848@gmail.com", "Shivam.Sahu@oodlestechnologies.com", "Test Subject","Test Message");
		
		logger.info("Mail controller template mail successfully send");
		
		return "Mail Sent Successfully..!!"; 
	}
	
	@RequestMapping(value= "/templatemail2" ,method= RequestMethod.GET)
	public String tempateMail2() {
		
		logger.info("Mail controller template mail 2");
		
		  String htmlMsg = "<html><body><h1>Im testing send a HTML email</h1>"      +"<img src='http://www.apache.org/images/asf_logo_wide.gif'></body></html>";

		
		mailService.sendMail("shivam4848@gmail.com", "Shivam.Sahu@oodlestechnologies.com", "Test Subject", htmlMsg);
		
		logger.info("Mail controller template mail 2 send seeccusfully");
		
		return"Mail Sent Successfully..!!";
	}
}