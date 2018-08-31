package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.User;
import com.oodles.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Map createUser(User user) {
		Map result = new HashMap<>();
		String name = user.getName();
		String email = user.getEmail();
		String phoneNumber = user.getPhoneNumber();
		String country = user.getCountry();
		String password = user.getPassword();
		User userName = userRepository.findByName(name);
		User userEmail = userRepository.findByEmail(email);
		User userNumber = userRepository.findByPhoneNumber(phoneNumber);

		if (userName == null && userEmail == null && userNumber == null) {
			User newUser = new User();
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setPhoneNumber(phoneNumber);
			newUser.setPassword(password);
			newUser.setCountry(country);
			userRepository.save(newUser);
			result.put("responseMessage", "success");
			return result;
		}
		result.put("responseMessage", "error");
		return result;
	}

	// get all users
	public List<User> retrieveAllUser() {
		List<User> result = userRepository.findAll();
		return result;
	}

	// get all user by id
	public Optional<User> findUserById(String id) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		User result = value.get();
		if (value.isPresent() && (!result.getId().equals(id))) {
			return value;
		}
		return value;
	}

	// delete user by id
	public User deleteUser(String id) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));

		User result = value.get();
		if (value.isPresent() && (!result.getId().equals(id))) {

			userRepository.deleteById(Long.parseLong(id));
		}
		return result;
	}
	
//update user
	public User updateUser(String id, String name, String email, String password, String mobilenumber, String country) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		User user = value.get();
		if (value.isPresent() && (!user.getName().equalsIgnoreCase(name)) && (!user.getEmail().equalsIgnoreCase(email))
				&& (!user.getPassword().equalsIgnoreCase(password))
				&& (!user.getPhoneNumber().equalsIgnoreCase(mobilenumber))) {
			User newUsers = new User();
			newUsers.setId(Long.parseLong(id));
			newUsers.setName(name);
			newUsers.setCountry(country);
			newUsers.setEmail(email);
			newUsers.setPhoneNumber(mobilenumber);
			newUsers.setPassword(password);
			userRepository.save(newUsers);
		}
		return user;
	}
}
