package com.oodles.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oodles.dto.RoleDto;
import com.oodles.dto.StringConstant;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.RoleService;

@Controller
public class RoleController {
@Autowired
private RoleService roleService;


/**
 * Create a new role
 * @param roleType
 * @return
 */
@PreAuthorize("hasRole('ADMIN')")

@RequestMapping(method = RequestMethod.POST, value = "/v1/admin/role/{roleType}")
@ResponseBody
public Map createUser(@PathVariable String roleType)  {
	 Map result=null;
	
	
	 result=roleService.createRole(roleType);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);
	
}
/**
 *  Create a new admin
 * @param userRoleDTO
 * @return
 */
@PreAuthorize("hasRole('ADMIN')")

@RequestMapping(method = RequestMethod.POST, value = "/v1/admin/role/assign")
@ResponseBody
public Map assignRole(@RequestBody RoleDto userRoleDTO)  {
	 Map result=null;
	
	
	 result=roleService.assignRole(userRoleDTO);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);
	
}
}
