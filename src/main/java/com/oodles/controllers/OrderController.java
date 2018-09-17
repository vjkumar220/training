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

import com.oodles.dto.BuyOrderDto;
import com.oodles.dto.MarketOrderDto;
import com.oodles.dto.OrderDto;
import com.oodles.dto.SellOrderDto;
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

	/**
	 *  Create Limit Order
	 * @param orderDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/createlimitorder")

	public Map createLimitOrder(@RequestBody OrderDto orderDTO) {
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

	/**
	 *  Create Market Order
	 * @param marketOrderDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/createmarketorder")

	public Map createMarketOrder(@RequestBody MarketOrderDto marketOrderDTO) {
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

	/**
	 *  Create Buy Order
	 * @param orderDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/createbuyorder")

	public Map createBuyOrder(@RequestBody BuyOrderDto orderDTO) {
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

	/**
	 *  Create Sell Order
	 * @param orderDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/createsellorder")

	public Map createSellOrder(@RequestBody SellOrderDto orderDTO) {
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
	/**
	 * Get All Sell Order
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/v1/allsellorder")
		
		public List<SellOrder> viewAllSellOrder() {
			List<SellOrder> result = orderService.retrieveAllSellOrder();
			return result;
		}
     
	/**
	 * Get All Buy Order
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/v1/allbuyorder")
		
		public List<BuyOrder> viewAllBuyOrder() {
			List<BuyOrder> result = orderService.retrieveAllBuyOrder();
			return result;
		}	
}
