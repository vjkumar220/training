package com.oodles.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oodles.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
