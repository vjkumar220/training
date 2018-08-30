package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
	User findByMobilenumber(String mobilenumber);
}