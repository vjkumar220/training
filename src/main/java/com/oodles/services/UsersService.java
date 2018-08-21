package com.oodles.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.User;
import com.oodles.repository.UsersRepository;

@Service
public class UsersService {
	public static final Logger logger = LoggerFactory.getLogger(UsersService.class);
	@Autowired
	private UsersRepository usersRepository;

	public Map<String, Object> createUser(String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = usersRepository.findByName(name);
		if(user == null){
			User newUser=new User();
			newUser.setName(name);
				
			usersRepository.save(newUser);
			
			result.put("responseMessage", "success");
		}
		return result;
	}
	
}