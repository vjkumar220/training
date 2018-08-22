package com.oodles.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
//create
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
	
	//Update
	
	public User updateUser(String id,String name)
	{
		Optional<User> value=usersRepository.findById(Long.parseLong(id));
		 User user = value.get();
		 if(value.isPresent() && (!user.getName().equalsIgnoreCase(name)))
		 
	
		{
			 User newUsers=new User();
			 newUsers.setId(Long.parseLong(id));
			newUsers.setName(name);
			usersRepository.save(newUsers);
		}		
		return user;
	}
	
	//Delete 
	public  User deleteUser(String id) {
		
		Optional<User> user = usersRepository.findById(Long.parseLong(id));
		 User result = user.get();
		if(user.isPresent()&& (!result.getId().equals(id)))
		{
			User newUser=new User();
			
				
			usersRepository.deleteById(Long.parseLong(id));
			
			
		}
		return result;
	}
}

	
	
	
	
	
	
	
	
	
	
