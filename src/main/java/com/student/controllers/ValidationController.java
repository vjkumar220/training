package com.student.controllers;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.student.domain.Validation;
import com.student.exception.StudentNotFoundException;
import com.student.repositry.ValidationRepository;

@RestController
public class ValidationController {
	
	@Autowired
	ValidationRepository validationRepository;
	
	@GetMapping("/validation")
	public List<Validation> retrieveAllValidations() {
		return validationRepository.findAll();
	}

	
	@GetMapping("/validation/{id}")
	public Resource<Validation> retrieveValidation(@PathVariable long id) {
		Optional<Validation> validation =validationRepository.findById(id);

		if (!validation.isPresent())
			throw new StudentNotFoundException("id-" + id);

		Resource<Validation> resource = new Resource<Validation>(validation.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllValidations());

		resource.add(linkTo.withRel("all-students"));

		return resource;
	}
	
	@DeleteMapping("/validation/{id}")
	public void deleteStudent(@PathVariable long id) {
		validationRepository.deleteById(id);
	}
	
	@PostMapping("/validation")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Validation validation) {
		Validation saveValidation = validationRepository.save(validation);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveValidation.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping("/validation/{id}")
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody Validation validation, @PathVariable long id) {

		Optional<Validation> validationOptional = validationRepository.findById(id);

		if (!validationOptional.isPresent())
			return ResponseEntity.notFound().build();

		validation.setId(id);
		
		validationRepository.save(validation);

		return ResponseEntity.noContent().build();
	}
	
}
