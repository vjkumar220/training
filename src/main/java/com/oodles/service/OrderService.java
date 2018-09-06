package com.oodles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyOrder;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.SellOrderRepository;

@Service
public class OrderService {

	@Autowired
	BuyOrderRepository buyOrderRepository;
	
	@Autowired
	SellOrderRepository sellOrderRepository;
	
	public String createBuyOrder(BuyOrder buyOrder) {
		
		
		return null;
	}
}
