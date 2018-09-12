package com.oodles.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.DTO.BuyOrderDTO;
import com.oodles.DTO.MarketOrderDTO;
import com.oodles.DTO.OrderDTO;
import com.oodles.DTO.SellOrderDTO;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.models.BuyOrder;
import com.oodles.models.SellOrder;
import com.oodles.services.OrderService;

@RestController
public class OrderController {
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;

	// Create Limit Order
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

	// Create Market Order
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

	// Create Buy Order
	@RequestMapping(method = RequestMethod.POST, value = "/v1/createbuyorder")

	public Map createBuyOrder(@RequestBody BuyOrderDTO orderDTO) {
		Map result = null;
		try {
			logger.info("Entered in create order");
			logger.info("userWalletDTO =" + orderDTO);
			result = orderService.createBuyOrder(orderDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);

		} catch (ResourceNotFoundException exception) {

			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id Does not exist", null, result);
		}
	}

	// Create Sell Order
	@RequestMapping(method = RequestMethod.POST, value = "/v1/createsellorder")

	public Map createSellOrder(@RequestBody SellOrderDTO orderDTO) {
		Map result = null;
		try {
			logger.info("Entered in create order");
			logger.info("userWalletDTO =" + orderDTO);
			result = orderService.createSellOrder(orderDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);

		} catch (ResourceNotFoundException exception) {

			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id Does not exist", null, result);
		}
	}
	//Get All Sell Order
	
		@GetMapping(value = "/allsellorder")
		public List<SellOrder> viewAllSellOrder() {
			List<SellOrder> result = orderService.retrieveAllSellOrder();
			return result;
		}
     
		//Get All Buy Order
		@GetMapping(value = "/allbuyorder")
		public List<BuyOrder> viewAllBuyOrder() {
			List<BuyOrder> result = orderService.retrieveAllBuyOrder();
			return result;
		}	
}
