package com.oodles.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.DTO.MarketOrderDTO;
import com.oodles.DTO.OrderDTO;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.OrderService;

@RestController
public class OrderController {
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
//Create Limit Order
	@RequestMapping(method = RequestMethod.POST, value = "/v1/createlimitorder")

	public Map createLimitOrder(@RequestBody OrderDTO orderDTO) {
		Map result = null;
		try {
			logger.info("Entered in create order");
			logger.info("userWalletDTO =" + orderDTO);
			result = orderService.createLimitOrder(orderDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);

		} catch (ResourceNotFoundException exception) {

			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id Does not exist", null, result);
		}
	}
	//Create Market Order
		@RequestMapping(method = RequestMethod.POST, value = "/v1/createmarketorder")

		public Map createMarketOrder(@RequestBody MarketOrderDTO marketOrderDTO) {
			Map result = null;
			try {
				logger.info("Entered in create order");
				logger.info("userWalletDTO =" + marketOrderDTO);
				result = orderService.createMarketOrder(marketOrderDTO);
				logger.info("created fiatwallet");
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);

			} catch (ResourceNotFoundException exception) {

				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id Does not exist", null, result);
			}		
	}

}
