package com.oodles.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.Order;
import com.oodles.domain.User;
import com.oodles.domain.Users;
import com.oodles.repository.UserOrderRepository;

@Service
public class UserOrderService {
	public static final Logger logger = LoggerFactory.getLogger(UserOrderService.class);
	@Autowired
	private UserOrderRepository userorderRepository;
	public List<Order> one()
	{
		List<Order> cus=userorderRepository.findAll();
		return cus;
	}
	
	
	
}
