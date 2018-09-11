package com.oodles.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.OrdersDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.OrdersService;
import com.oodles.util.ResponseHandler;

@RequestMapping("/v1")
@RestController
public class OrdersController {
	@Autowired
	OrdersService ordersService;
	private Map result = null;

	@PostMapping(value = "/orders")
	public Map createOrder(@RequestBody OrdersDto ordersDto) {
		try {
			result = ordersService.createOrder(ordersDto);
			if (result.containsKey("sucess")) {
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
			}
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

}
