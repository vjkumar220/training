package com.oodles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.dto.UserDto;
import com.oodles.models.User;
import com.oodles.repository.UserRepository;


@Service
public class UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	/**
	 * create
	 * @param user
	 * @return
	 */
	public Map<String, Object>  createUser(UserDto user) {
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
			newUser.setEnabled("Inactive");
			userRepository.save(newUser);
			result.put("responseMessage", "success");
			return result;
		}
		result.put("responseMessage", "Email or mobile number exists");
		return result;
	}
	/**
	 * Get All detail
	 * @return
	 */
	public List<User> retrieveAllUser(){
		
		return userRepository.findAll();
	}
	

	
	
	/**
	 * Get All Detail by id
	 * @param id
	 * @return
	 */
	public User retriveUser(String id)
	{
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		
		User result = value.get();
		
      return result;
}
	/**
	 * Update a particular user
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param mobilenumber
	 * @param country
	 * @return
	 */
	
	public User updateUser(String id,String name,String email,String password,String mobilenumber,String country)
	{
		Optional<User> value=userRepository.findById(Long.parseLong(id));
		 User user = value.get();
		 if(value.isPresent() && (!user.getName().equalsIgnoreCase(name))&&(!user.getEmail().equalsIgnoreCase(email))&&(!user.getPassword().equalsIgnoreCase(password))&&(!user.getMobilenumber().equalsIgnoreCase(mobilenumber)))
		 
		{
			 User newUsers=new User();
			 newUsers.setId(Long.parseLong(id));
			newUsers.setName(name);
			newUsers.setCountry(country);
			newUsers.setEmail(email);
			newUsers.setMobilenumber(mobilenumber);
			newUsers.setPassword(password);
			userRepository.save(newUsers);
		}		
		return user;
	}
	
	/**
	 * delete a user
	 * @param id
	 * @return
	 */
public  User deleteUser(String id) {
		
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		 User result = user.get();
		if(user.isPresent()&& (!result.getId().equals(id)))
		{
			User newUser=new User();
			
				
			userRepository.deleteById(Long.parseLong(id));
			
			
		}
		return result;
}
	
}