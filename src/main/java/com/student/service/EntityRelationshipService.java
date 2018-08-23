package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.domain.User;
import com.student.respsitory.UserRepository;

@Service
public class EntityRelationshipService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> onoToOne(){
		
		return userRepository.findAll();
	}

}
