package com.oodles.service;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyOrder;
import com.oodles.domain.SellOrder;
import com.oodles.enumeration.OrderStatus;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.SellOrderRepository;
@Service
public class OrderMatchingService {

	@Autowired
	BuyOrderRepository buyOrderRepository;
	
	@Autowired
	SellOrderRepository sellOrderRepository;
	
	
	public Set<BuyOrder> buyList() {
		
		return buyOrderRepository.findAllByBuyOrderStatus(OrderStatus.PENDING.toString());
	}
	
	public List<SellOrder> sellList(){
		
		return sellOrderRepository.findAllBySellOrderStatus(OrderStatus.PENDING);
	}
	
	
}
