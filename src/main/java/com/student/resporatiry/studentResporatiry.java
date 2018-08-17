package com.student.resporatiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.student;

public interface studentResporatiry extends JpaRepository<student, Long> {

}
