package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.Order;
import com.oodles.domain.Users;
@Repository
public interface UserOrderRepository extends JpaRepository<Order, Integer>{
	Users findByName(String name);

	void saveAll(Users newUser);
}
