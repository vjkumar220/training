package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
}