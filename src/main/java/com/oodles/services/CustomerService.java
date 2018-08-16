package com.oodles.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.controller.CustomerRepository;
import com.oodles.domain.Customer;

import ch.qos.logback.classic.Logger;

@Service
public class CustomerService {

	org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository cusRepository;

	public Customer getCustomerInfo(Long id) {
		try {
			Customer cus = cusRepository.getOne(id);

			return cus;
		} catch (Exception e) {

			logger.error("inside catch of Customer service", e);
			return null;
		}
	}
}
