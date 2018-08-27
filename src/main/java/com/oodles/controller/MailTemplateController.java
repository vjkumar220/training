package com.oodles.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oodles.services.MailService;
@Controller
public class MailTemplateController {
	@Autowired
	MailService mailService;
	@RequestMapping(value="api/email/template", method=RequestMethod.GET)
	public String sendHtmlEmail() throws MessagingException {
		
        String htmlMsg = "<h1>Im testing send a HTML email</h1>"+"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";
         
        mailService.sendMail("shubhamsinghgu@gmail.com", "Shubham.singh@oodlestechnologies.com", "First Mail",htmlMsg);
 
        return "Email Sent!";
    }
	
}
	
	
	
	
	
	