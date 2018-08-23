package com.oodles.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.oodles.domain.Users;
import com.oodles.repository.UserOrderRepository;

@Service
public class UserOrderService {
	public static final Logger logger = LoggerFactory.getLogger(UserOrderService.class);
	@Autowired
	private UserOrderRepository userorderRepository;
	
	
	public Map<String, Object> createUser(String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		Users user = userorderRepository.findByName(name);
		if(user == null){
			Users newUser=new Users();
			newUser.setName(name);
				
			userorderRepository.saveAll(newUser);
			
			result.put("responseMessage", "success");
		}
		return result;
	}
	
}
