package com.student.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.domain.User;
import com.student.repository.UserRepository;

@Service
public class EntityRelationshipService {
	
	Logger logger = LoggerFactory.getLogger(EntityRelationshipService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> onoToOne(){
		
		logger.info("Entity Relationship Service one to one");
		
		return userRepository.findAll();
	}
	
	public List<User>  oneToMany(){
		
		logger.info("Entity Relationship Service one to many");
		
		return userRepository.findAll();
	}

	
}
