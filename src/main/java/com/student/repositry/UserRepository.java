package com.student.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.User;



public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

}
