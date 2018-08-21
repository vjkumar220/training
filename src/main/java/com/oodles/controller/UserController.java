package com.oodles.controller;

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

import com.oodles.domain.User;
import com.oodles.example.ResourceNotFoundException;
import com.oodles.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
@Autowired
private UserRepository userRepository;

//Get All User

@GetMapping("/users")
public List<User> getAllNotes() {
    return userRepository.findAll();
}

//Create a new User

@PostMapping("/saveusers")
public User createUser(@Valid @RequestBody User user) {
    return userRepository.save(user);
}


//Get a single user

@GetMapping("/getusers/{id}")
public User getUserById(@PathVariable(value = "id") Long userId) {
    return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
}

//Update a user

@PutMapping("/updateusers/{id}")
public User updateUser(@PathVariable(value = "id") Long userId,
                                        @Valid @RequestBody User userDetails) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    user.setName(userDetails.getName());
    user.setEmail(userDetails.getEmail());
    user.setMobile(userDetails.getMobile());
    user.setPassword(userDetails.getPassword());
    user.setCountry(userDetails.getCountry());
    User updatedUser = userRepository.save(user);
    return updatedUser;
}

//Delete a User
@DeleteMapping("/deleteusers/{id}")
public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    userRepository.delete(user);

    return ResponseEntity.ok().build();
}
}
