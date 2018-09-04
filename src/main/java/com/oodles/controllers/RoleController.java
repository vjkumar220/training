package com.oodles.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oodles.exceptions.ResourceNotFound;
import com.oodles.exceptions.UserNotFoundException;
import com.oodles.models.RoleDTO;
import com.oodles.services.RoleService;

@Controller
public class RoleController {
@Autowired
private RoleService roleService;


//Create a new role
@RequestMapping(method = RequestMethod.GET, value = "/rest/createrole/{roleType}")
@ResponseBody
public Map createUser(@PathVariable String roleType)  {
	 Map result=null;
	
	try{
	 result=roleService.createRole(roleType);
		return ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, result);
	}
	catch(UserNotFoundException exception){
		return ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
	}
}
// Create a new admin
@RequestMapping(method = RequestMethod.GET, value = "/rest/assignrole/")
@ResponseBody
public Map createUser(@RequestBody RoleDTO roleDTO)  {
	 Map result=null;
	
	try{
	 result=roleService.assignRole(roleDTO);
		return ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, result);
	}
	catch(UserNotFoundException exception){
		return ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
	}
}
}
