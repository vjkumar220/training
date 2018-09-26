package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.BuyOrderDto;
import com.oodles.dto.SellOrderDto;
import com.oodles.service.OrderService;
import com.oodles.util.ResponseHandler;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Create the buy order request
	 * 
	 * @param buyOrderDto
	 * @return
	 */
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "v1/user/buy/order")
	public Map<String, Object> buyOrderGenerated(@Valid @RequestBody BuyOrderDto buyOrderDto) {
		String result = orderService.buyOrder(buyOrderDto);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, SUCCESS, null, result);
	}

	/**
	 * Create the sell order request
	 * 
	 * @param sellOrderDto
	 * @return
	 */
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "v1/user/sell/order")
	public Map<String, Object> sellOrderGenerated(@Valid @RequestBody SellOrderDto sellOrderDto) {
		String result = orderService.sellOrder(sellOrderDto);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, SUCCESS, null, result);
	}

}
