package com.student.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
public class SmsVerificationController {
    
	@GetMapping(value = "/verify")//mapping for none
	 @ResponseBody
	public String view() {
	
	Twilio.init("ACe2f058ec28715b76bdeaac2da52d1324", "66384b401747451a0cf47404f3ae8d4c");
    Message message = Message.creator(
            new PhoneNumber("+918700153661"),
            new PhoneNumber("+19852384430"),
            "body")
        .create();

    return message.getSid();
}
}
