package com.student.conroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.domain.student;

public class studentController {

	Logger logger = LoggerFactory.getLogger(studentController.class);

	@Autowired
	private com.student.service.studentService studentService;

	@RequestMapping(value = "/getstudent/{stuId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<student> getEmployee(@PathVariable String stuId) {
		logger.info("getEmployee started");
		student emp = studentService.getStudentInfo(Long.parseLong(stuId));

		ResponseEntity<student> entity = new ResponseEntity<student>(emp, HttpStatus.OK);
		logger.info("getEmployee ends");
		return entity;

	}
}
