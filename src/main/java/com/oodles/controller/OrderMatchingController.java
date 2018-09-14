package com.oodles.controller;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.SellOrder;
import com.oodles.service.OrderMatchingService;

@RestController
public class OrderMatchingController {
	
	@Autowired
	private OrderMatchingService orderMatchingService;
	
	@GetMapping("/sell/order")
	public List<SellOrder> sellList(){
		List<SellOrder> sellList = orderMatchingService.sellList();
		System.out.println(sellList);
		return sellList;
	}

}
