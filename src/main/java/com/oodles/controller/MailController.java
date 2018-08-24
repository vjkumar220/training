package com.oodles.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.services.MailService;



@RestController
public class MailController {
	
	@Autowired
	MailService mailService;

	@RequestMapping(value= "/sendmail" ,method= RequestMethod.GET)
	public String sendMail() {
		
		mailService.sendMail("shubhamsinghgu@gmail.com", "Shivam.Sahu@oodlestechnologies.com", "First Mail", "First mail from spring boot");
		
		return"Mail Sent Successfully..!!";
	}
}