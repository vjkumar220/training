package com.oodles.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oodles.domain.PersonForm;

@Controller
public class LoginController implements WebMvcConfigurer {

	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/results").setViewName("results");
	    }

	    @GetMapping("/showform")
	    public String showForm(PersonForm personForm) {
	        return "form";
	    }

	    @PostMapping("/showform")
	    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

	        if (bindingResult.hasErrors()) {
	            return "form";
	        }

	        return "redirect:/results";
	    }
}
