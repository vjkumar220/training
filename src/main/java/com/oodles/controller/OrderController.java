package com.oodles.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.BuyOrderDto;
import com.oodles.dto.SellOrderDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.OrderService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping("/v1")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * Create the buy order request
	 * @param buyOrderDto
	 * @return
	 */

	@PostMapping(value = "user/buy/order")
	public Map<String , Object> buyOrderGenerated(@Valid @RequestBody BuyOrderDto buyOrderDto) {
		  String result = orderService.buyOrder(buyOrderDto);
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, "success", null, result);
	}
		/**
		 * Create the sell order request
		 * @param sellOrderDto
		 * @return
		 */
	@PostMapping(value = "user/sell/order")
	public Map<String , Object> sellOrderGenerated(@Valid @RequestBody SellOrderDto sellOrderDto) {
		 String result = orderService.sellOrder(sellOrderDto);
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, "success", null, result);
	}
	
}