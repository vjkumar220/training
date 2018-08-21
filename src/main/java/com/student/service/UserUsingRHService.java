package com.student.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.domain.UserUsingRH;
import com.student.respsitory.UserRepositryUsingRS;

@Service
public class UserUsingRHService {
	
	
	@Autowired
	private UserRepositryUsingRS userRepository;
	
	
	public Map<String, Object> generateUser(String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		UserUsingRH user = userRepository.findByName(name);
		if(user == null){
			UserUsingRH newUser=new UserUsingRH();
			newUser.setName(name);
				
			userRepository.save(newUser);
			
			result.put("responseMessage", "success");
		}
		return result;
	}


}
