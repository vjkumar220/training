package com.student.controllers;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.service.StudentService;
import com.student.domain.Student;

@Controller
public class StudentController {

	Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/getStudent/{stuId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Student> getEmployee(@PathVariable String stuId) {
		logger.info("getEmployee started");
		Student emp = studentService.getStudentInfo(Long.parseLong(stuId));

		ResponseEntity<Student> entity = new ResponseEntity<Student>(emp, HttpStatus.OK);
		logger.info("getEmployee ends");
		return entity;
	}
}
