package com.oodles.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oodles.domain.Customer;
import com.oodles.services.CustomerService;

@Controller
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/getcustomer/{cusId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Customer> getCustomer(@PathVariable String cusId) {
		logger.info("getCustomer started");
		Customer cus = customerService.getCustomerInfo(Long.parseLong(cusId));

		ResponseEntity<Customer> entity = new ResponseEntity<Customer>(cus, HttpStatus.OK);
		logger.info("getCustomer ends");
		return entity;

	}
}
