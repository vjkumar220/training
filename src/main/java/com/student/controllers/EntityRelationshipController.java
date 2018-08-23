package com.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.domain.User;
import com.student.service.EntityRelationshipService;
import com.student.service.UserService;

@RestController
public class EntityRelationshipController {
	
	@Autowired
	private EntityRelationshipService entityRelationshipService;

	@RequestMapping(value  ="/oneone" , method=RequestMethod.GET)
	
	public List<User> oneToOne(){
		List<User> result=entityRelationshipService.onoToOne();
		return result;
	}
	
}
