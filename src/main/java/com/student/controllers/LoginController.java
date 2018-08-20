package com.student.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	//applying the log 
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@PostMapping(value = "/login")//mapping for login
	public String enter(@RequestParam("name") String name, @RequestParam("pass") String pass, Model modal) {

		System.out.println("i m in enter using sop");

		LOGGER.trace("we getting strarted by trace", name);
		LOGGER.debug("we gettig started by debug", name);
		LOGGER.info("we getting started by info", name);
		LOGGER.warn("by warn", name);
		LOGGER.error("by error", name);

		modal.addAttribute("name", name);
		modal.addAttribute("pass", pass);

		return "show";
	}

	@GetMapping(value = "/")//mapping for none
	public String view() {

		System.out.println("i m in view using sop");

		return "login";
	}

}
