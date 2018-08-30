package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oodles.domain.User;
@Repository
public interface UserRepositry extends JpaRepository<User, Long> {
	
	User findByName(String name);
	User findByEmail(String email);
	User findByPhoneNumber(String phoneNumber);
	User findByCountry(String country);
}
