package com.student.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.domain.student;
import com.student.resporatiry.studentResporatiry;



@Service
public class studentService {

	Logger logger = LoggerFactory.getLogger(studentService.class);

	@Autowired
	private studentResporatiry stuResporatiry;

	public student getStudentInfo(Long id) {
		try {
			student stu = stuResporatiry.getOne(id);

			return stu;
		} catch (Exception e) {

			logger.error("inside catch of employee service", e);
			return null;
		}
	}

}
