package com.student.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.Student;

public interface StudentRespository extends JpaRepository<Student, Long> {

}
