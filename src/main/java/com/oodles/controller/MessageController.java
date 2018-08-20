package com.oodles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
public class MessageController {
	@GetMapping(value = "/verify")//mapping for none
	@ResponseBody
	public String view() {
	
	Twilio.init("ACe46a7e8c2e88ee736b1eece22bb8e74b", "1e709fed396e810515968114c50e87e6");
    Message message = Message.creator(
            new PhoneNumber("+917985253313"),
            new PhoneNumber("+13203145689"),
            "body")
        .create();

    return message.getSid();
}
}