package com.oodles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.User;
import com.oodles.repository.UserRepository;


@Service
public class UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
//create
	public Map<String, Object>  createUser(User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		String name=user.getName();
		String password=user.getPassword();
		String country=user.getCountry();
		String email=user.getEmail();
		String mobilenumber=user.getMobilenumber();
		User useremail = userRepository.findByEmail(user.getEmail());
		User usermobilenumber=userRepository.findByMobilenumber(user.getMobilenumber());
		if(useremail == null&&usermobilenumber==null){
			User newUser=new User();
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setMobilenumber(mobilenumber);
			newUser.setPassword(password);
			newUser.setCountry(country);
			userRepository.save(newUser);
			result.put("responseMessage", "success");
		}
		return result;
	}
	//Get All detail
	public List<User> retrieveAllUser(){
		List<User> result = userRepository.findAll();
		return result;
	}
	

	
	
//Get All Detail by id
	public User retriveUser(String id)
	{
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		
		User result = value.get();
		
      return result;
}
}