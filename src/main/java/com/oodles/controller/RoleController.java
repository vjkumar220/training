package com.oodles.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.Role;
import com.oodles.domain.User;
import com.oodles.dto.UserRoleDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.RoleService;
import com.oodles.util.ResponseHandler;

@RestController
public class RoleController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	RoleService roleService;

	// creating role
	@PostMapping(value = "/role")
	public Map createUser(@Valid @RequestBody Role role) {
		Map output = null;
		logger.info("RoleController-create value start");
		try {
			logger.info("UserController - create value in try");

			output = roleService.createRole(role);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);
		} catch (ResourceNotFoundException e) {
			logger.info("RoleController - create value in catch'");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);
		}
	}

	// Assign role to user
	@PostMapping(value = "/assignrole")
	public Map assingRoleToUser(@RequestBody UserRoleDto userRoleDto) {
		Map output = null;
		logger.info("Role controller - assign role to user");
		try {
			logger.info("Role controller - assign role try to user");
			output = roleService.assignRole(userRoleDto);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);
		}
		return null;
	}

}
