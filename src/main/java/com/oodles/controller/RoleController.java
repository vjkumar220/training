package com.oodles.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.Role;
import com.oodles.dto.RoleDto;
import com.oodles.dto.UserRoleDto;
import com.oodles.service.RoleService;
import com.oodles.util.ResponseHandler;

@RestController
public class RoleController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	RoleService roleService;

	/**
	 * creating role
	 * 
	 * @param role
	 * @return
	 */
	@PostMapping(value = "v1/admin/role")
	public Map<String, Object> createUser(@Valid @RequestBody RoleDto role) {
		Map<Object, Object> output = roleService.createRole(role);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);
	}

	/**
	 * Assign role to user
	 * 
	 * @param userRoleDto
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "v1/admin/assign/role")
	public Map<String, Object> assingRoleToUser(@RequestBody UserRoleDto userRoleDto) {
		Map<Object, Object> output = roleService.assignRole(userRoleDto);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);
	}

}
