package com.student.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.domain.User;
import com.student.service.EntityRelationshipService;

@RestController
public class EntityRelationshipController {
	
	Logger logger = LoggerFactory.getLogger(EntityRelationshipController.class);

	@Autowired
	private EntityRelationshipService entityRelationshipService;

	@RequestMapping(value = "/oneone", method = RequestMethod.GET)
		
	public List<User> oneToOne() {
		
		logger.info("EntityRelationShip controller one to one");
		
		List<User> result = entityRelationshipService.onoToOne();
		
		logger.info("EntityRelationShip controller one to one get the result");
		
		return result;
	}

	@RequestMapping(value = "/onetomany", method = RequestMethod.GET)

	public List<User> oneToMany() {
		
		logger.info("EntityRelationShip controller one to many");
		
		List<User> result = entityRelationshipService.oneToMany();
		
		logger.info("EntityRelationShip controller one  to many result");
		
		return result;
	}

	@RequestMapping(value = "/manytomany", method = RequestMethod.GET)

	public List<User> manyToMany() {
		
		logger.info("EntityRelationShip controller many to many");
		
		List<User> result = entityRelationshipService.oneToMany();
		
		logger.info("EntityRelationShip controller many to many result");
		
		return result;
	}
}
