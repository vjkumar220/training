package com.oodles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloForm {
	
	@PostMapping("/detail")
	public String getting(@RequestParam("name")String  name ,@RequestParam("message")String message ,Model model)
	{ 
		model.addAttribute("name",name); 
		model.addAttribute("message",message); 
		return "hv";	
	}
	@GetMapping("/")
	public String accept()
	{
		System.out.println("one started");
		System.out.println("one finished");
		return "hz";	
	}
	{
		
	}
	
}
