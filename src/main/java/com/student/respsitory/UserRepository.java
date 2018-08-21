package com.student.respsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.User;



public interface UserRepository extends JpaRepository<User, Long> {

}
