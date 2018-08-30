package com.oodles.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oodles.domain.User;
import com.oodles.repository.UserRepositry;

@Service
public class UserService {
	
	@Autowired
	UserRepositry userRepositry;
	
	public ResponseEntity<Object> createUser(User user){
		String name=user.getName();
		String email=user.getEmail();
		String phoneNumber=user.getPhoneNumber();
		String country=user.getCountry();
		String password=user.getPassword();
		User userName = userRepositry.findByName(name);
		User userEmail= userRepositry.findByEmail(email);
		User userNumber=userRepositry.findByPhoneNumber(phoneNumber);
		User userCountry=userRepositry.findByCountry(country);
		if(userName==null && userEmail==null && userNumber==null && userCountry==null) {
			User newUser = new User();
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setPhoneNumber(phoneNumber);
			newUser.setPassword(password);
			newUser.setCountry(country);
			userRepositry.save(newUser);
			return new ResponseEntity<>("User Registered Sucessfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("You are the existing user", HttpStatus.BAD_REQUEST);
	}

}
