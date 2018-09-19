package com.oodles.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.models.BuyOrder;
import com.oodles.models.SellOrder;
import com.oodles.services.OrderMatchingService;

@RestController
public class OrderMatchingController {

	@Autowired
	private OrderMatchingService orderMatchingService;
	@RequestMapping(method = RequestMethod.GET, value = "/sell/order")
	public List<SellOrder> sellList(){
		return orderMatchingService.sellList();
	}


	@RequestMapping(method = RequestMethod.GET, value = "/buy/order")
	public List<BuyOrder> buyList(){
		return orderMatchingService.buyList();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/v1/DoTransaction")

	public Map orderMatch() {
		Map result = null;
		
			result = orderMatchingService.orderMatch();
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
	}}
	

