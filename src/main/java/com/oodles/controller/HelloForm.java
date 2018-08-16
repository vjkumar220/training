package com.oodles.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloForm {

	Logger logger = LoggerFactory.getLogger(HelloForm.class);//for Logging 

	@PostMapping("/detail") //Mapping the data after submission 
	public String getting(@RequestParam("name") String name, @RequestParam("message") String message, Model model) {
		logger.debug("Debugging method");
		logger.info("Info method");
		logger.warn("Warning method");
		logger.error("Error method");

		model.addAttribute("name", name);
		model.addAttribute("message", message);
		logger.debug("End Debugging method");
		logger.info("End Info method");
		logger.warn("End Warning method");
		logger.error("End Error method");
		return "hv";
	}

	@GetMapping("/")     //Getting the form
	public String accept() {
		logger.debug("Debugging method");
		logger.info("Info method");
		logger.warn("Warning method");
		logger.error("Error method");

		return "hz";
	}

	{

	}

}
