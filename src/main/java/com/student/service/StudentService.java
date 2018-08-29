package com.student.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.domain.Student;
import com.student.repository.StudentRespository;

@Service
public class StudentService {

	Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRespository stuResporatiry;

	public Student getStudentInfo(Long id) {
		try {
			Student stu = stuResporatiry.getOne(id);

			return stu;
		} catch (Exception e) {

			logger.error("inside catch of employee service", e);
			return null;
		}
	}

}
