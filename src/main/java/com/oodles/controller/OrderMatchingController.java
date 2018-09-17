package com.oodles.controller;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.BuyOrder;
import com.oodles.domain.SellOrder;
import com.oodles.service.OrderMatchingService;

@RestController
public class OrderMatchingController {
	
	@Autowired
	private OrderMatchingService orderMatchingService;
	
	@GetMapping("/sell/order")
	public List<SellOrder> sellList(){
		return orderMatchingService.sellList();
	}

	
	@GetMapping("/buy/order")
	public List<BuyOrder> buyList(){
		return orderMatchingService.buyList();
	}
	
}
