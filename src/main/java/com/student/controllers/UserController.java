package com.student.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.domain.User;
import com.student.exception.ResourceNotFoundException;
import com.student.respsitory.UserRepository;
import com.student.service.UserService;
import com.student.util.ResponseHandler;

@RestController
@RequestMapping("/api")

public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Create a new User
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User User) {
		return userRepository.save(User);
	}

	// Get a Single User
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long UserId) {
		return userRepository.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
	}

	// Update a User
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long UserId, @Valid @RequestBody User UserDetails) {

		User User = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));

		User.setName(UserDetails.getName());
		User.setPassword(UserDetails.getPassword());
		User.setEmail(UserDetails.getEmail());

		User updatedUser = userRepository.save(User);
		return updatedUser;
	}

	// Delete a User
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long UserId) {
		User User = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));

		userRepository.delete(User);

		return ResponseEntity.ok().build();
	}

	@Autowired
	private UserService userService;

	Map output = null;

	// Creating User

	@RequestMapping(value = "/getuser/{name}/{email}", method = RequestMethod.GET)
	@ResponseBody
	public Map generateUser(@PathVariable String name, @PathVariable String email) {

		try {
			logger.info("create try");
			output = userService.generateUser(name, email);

			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, output);
		} catch (ResourceNotFoundException e) {

			logger.info("create catch Exception", e);
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);
		}

	}

	// Update Controller
	User update = null;

	@RequestMapping(value = "/updateuser/{id}/{name}/{email}", method = RequestMethod.GET)

	public @ResponseBody Map updateUser(@PathVariable String id, @PathVariable String name,
			@PathVariable String email) {

		try {
			logger.info("update try");

			update = userService.updateUser(id, name, email);

			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, update);

		} catch (ResourceNotFoundException ex) {
			logger.info("update catch1", ex);
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		} catch (NoSuchElementException ex) {
			logger.info("update catch2", ex);

			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		}

	}

	// Delete

	User delete = null;

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public @ResponseBody Map deleteUser(@PathVariable String id) {
		try {
			logger.info("delete controller");

			delete = userService.deleteUser(id);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, update);

		} catch (ResourceNotFoundException ex) {
			logger.info("delete controller catch1", ex);
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		} catch (NoSuchElementException ex) {
			logger.info("delete controller catch2", ex);

			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, output);

		}

	}

}
