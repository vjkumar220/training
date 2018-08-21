package com.student.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.domain.UserUsingRH;
import com.student.exception.ResourceNotFoundException;
import com.student.service.UserUsingRHService;
import com.student.util.ResponseHandler;

@Controller
public class UserControllerUsingRS {

	Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private UserUsingRHService userUsingRHService;

	Map output = null;;

	@RequestMapping(value = "/getUser/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Map generateUser(@PathVariable String name) {

		try {

			output = userUsingRHService.generateUser(name);

			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);
		} catch (ResourceNotFoundException e) {

			logger.info("Exception", e);
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);
		}

	}

}
