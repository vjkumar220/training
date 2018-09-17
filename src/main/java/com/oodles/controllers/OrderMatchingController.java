package com.oodles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.models.BuyOrder;
import com.oodles.models.SellOrder;
import com.oodles.services.OrderMatchingService;

@RestController
public class OrderMatchingController {

	@Autowired
	private OrderMatchingService orderMatchingService;
	
	@GetMapping("/sell/order")
	public List<SellOrder> sellList(){
		//List<SellOrder> sellList = orderMatchingService.sellList();
		return orderMatchingService.sellList();
	}

	
	@GetMapping("/buy/order")
	public List<BuyOrder> buyList(){
		//List<BuyOrder> sellList = orderMatchingService.buyList();
		return orderMatchingService.buyList();
	}
	
}
