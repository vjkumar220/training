package com.oodles.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.BuyOrderDto;
import com.oodles.dto.OrderDto;
import com.oodles.dto.SellOrderDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.OrderService;
import com.oodles.util.ResponseHandler;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	//Create the buy order request

	@PostMapping(value = "/buyOrder")
	public Map buyOrderGenerated(@Valid @RequestBody BuyOrderDto buyOrderDto) {
		 String result = null;
		try {
			result = orderService.buyOrder(buyOrderDto);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
		//Create the sell order request
	@PostMapping(value = "/sellOrder")
	public Map sellOrderGenerated(@Valid @RequestBody SellOrderDto sellOrderDto) {
		 String result = null;
		try {
			result = orderService.sellOrder(sellOrderDto);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
}
