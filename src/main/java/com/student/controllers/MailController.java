package com.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	MailService mailService;

	@RequestMapping(value= "/sendmail" ,method= RequestMethod.GET)
	public String sendMail() {
		
		mailService.sendMail("shivam4848@gmail.com", "Shivam.Sahu@oodlestechnologies.com", "Test Subject", "Test Message");
		
		return"Mail Sent Successfully..!!";
	}
	
	@RequestMapping(value ="/temptatemail" , method=RequestMethod.GET)
	public String templateMail() {
		
		mailService.templateMail("shivam4848@gmail.com", "Shivam.Sahu@oodlestechnologies.com", "Test Subject","Test Message");
		
		return "Mail Sent Successfully..!!"; 
	}
	
	@RequestMapping(value= "/temptatemail2" ,method= RequestMethod.GET)
	public String tempateMail2() {
		
		  String htmlMsg = "<html><body><h1>Im testing send a HTML email</h1>"      +"<img src='http://www.apache.org/images/asf_logo_wide.gif'></body></html>";

		
		mailService.sendMail("shivam4848@gmail.com", "Shivam.Sahu@oodlestechnologies.com", "Test Subject", htmlMsg);
		
		return"Mail Sent Successfully..!!";
	}
}