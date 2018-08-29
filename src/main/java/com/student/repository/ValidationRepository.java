package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.domain.Validation;


@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long>{

}
