package com.student.controllers;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.student.repository.ValidationRepository;

@RestController
public class ValidationController {
	
	Logger logger = LoggerFactory.getLogger(ValidationController.class);
	
	@Autowired
	ValidationRepository validationRepository;
	
	@GetMapping("/validation")
	public List<Validation> retrieveAllValidations() {
		
		logger.info("ValidationController retriveAllValidations");
		
		return validationRepository.findAll();
	}

	
	@GetMapping("/validation/{id}")
	public Resource<Validation> retrieveValidation(@PathVariable long id) {
		
		logger.info("ValidationController retriveValidations");
		
		Optional<Validation> validation =validationRepository.findById(id);

		if (!validation.isPresent())
			throw new StudentNotFoundException("id-" + id);

		Resource<Validation> resource = new Resource<Validation>(validation.get());

		//This will help you to create the link for api by using HATEOS dependency
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllValidations());

		resource.add(linkTo.withRel("all-students"));

		return resource;
	}
	
	@DeleteMapping("/validation/{id}")
	public void deleteValidation(@PathVariable long id) {
		
		logger.info("ValidationController deleteValidations");
		
		validationRepository.deleteById(id);
	}
	
	@PostMapping("/validation")
	public ResponseEntity<Object> createValidation(@Valid @RequestBody Validation validation) {
		
		logger.info("ValidationController createValidations");
		
		Validation saveValidation = validationRepository.save(validation);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveValidation.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping("/validation/{id}")
	public ResponseEntity<Object> updateValidation(@Valid @RequestBody Validation validation, @PathVariable long id) {

		logger.info("ValidationController updateValidations");
		
		Optional<Validation> validationOptional = validationRepository.findById(id);

		if (!validationOptional.isPresent())
			return ResponseEntity.notFound().build();

		validation.setId(id);
		
		validationRepository.save(validation);

		return ResponseEntity.noContent().build();
	}
	
}
