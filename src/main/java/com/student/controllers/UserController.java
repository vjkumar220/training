package com.student.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.domain.User;
import com.student.exception.ResourceNotFoundException;
import com.student.respsitory.UserRepository;

@RestController
@RequestMapping("/api")

public class UserController {

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

}
