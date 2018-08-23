package com.student.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part.IgnoreCaseType;
import org.springframework.stereotype.Service;

import com.student.controllers.StudentController;
import com.student.domain.User;
import com.student.respsitory.UserRepository;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepositry;

	// Creating new User

	public Map<String, Object> generateUser(String name, String email) {

		logger.info("user in generateUser method");

		Map<String, Object> result = new HashMap<String, Object>();
		User userName = userRepositry.findByName(name);
		User userEmail = userRepositry.findByName(email);
		if (userName == null && userEmail == null) {

			logger.info("user rh create method in if");

			User newUser = new User();
			newUser.setName(name);
			newUser.setEmail(email);

			userRepositry.save(newUser);

			result.put("responseMessage", "success");
		}
		return result;
	}

	// Updating Service

	public User updateUser(String id, String name, String email) {

		logger.info("user in updateUser method ");
		Optional<User> value = userRepositry.findById(Long.parseLong(id));
		User result = value.get();

		if (value.isPresent() && (!result.getName().equalsIgnoreCase(name))) {
			User newUser = new User();
			logger.info("user in updateUser method in if");
			newUser.setId(Long.parseLong(id));
			newUser.setName(name);
			newUser.setEmail(email);
			userRepositry.save(newUser);

		}
		return result;

	}

	// Delete

	public User deleteUser(String id) {
		logger.info("user in deleteUser method ");
		Optional<User> value = userRepositry.findById(Long.parseLong(id));

		User result = value.get();
		if (value.isPresent() && (!result.getId().equals(id))) {

			userRepositry.deleteById(Long.parseLong(id));
		}
		return result;
	}

}
