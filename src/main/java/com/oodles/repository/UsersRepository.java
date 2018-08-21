package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.User;

	@Repository
	public interface UsersRepository extends JpaRepository<User,Long> {
		User findByName(String name);
	}


