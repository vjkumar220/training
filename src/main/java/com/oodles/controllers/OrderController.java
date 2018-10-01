package com.oodles.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.BuyOrderDto;
import com.oodles.dto.SellOrderDto;
import com.oodles.dto.StringConstant;
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
	 *//*
	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/order/limit")

	public Map createLimitOrder(@RequestBody OrderDto orderDTO) {
		Map result = null;
		
			logger.info("Entered in create order");
			logger.info("userWalletDTO =" + orderDTO);
			result = orderService.createLimitOrder(orderDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

		
	}

	*//**
	 *  Create Market Order
	 * @param marketOrderDTO
	 * @return
	 *//*
	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/order/market")

	public Map createMarketOrder(@RequestBody MarketOrderDto marketOrderDTO) {
		Map result = null;
		
			logger.info("Entered in create order");
			logger.info("userWalletDTO =" + marketOrderDTO);
			result = orderService.createMarketOrder(marketOrderDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

		
	}
*/
	/**
	 *  Create Buy Order
	 * @param orderDTO
	 * @return
	 */
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/order/buy")

	public Map createBuyOrder(@RequestBody BuyOrderDto orderDTO) {
		Map result = null;
		
			logger.info("Entered in create order");
			logger.info("userWalletDTO =" + orderDTO);
			result = orderService.createBuyOrder(orderDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

		
	}

	/**
	 *  Create Sell Order
	 * @param orderDTO
	 * @return
	 */
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/order/sell")

	public Map createSellOrder(@RequestBody SellOrderDto orderDTO) {
		Map result = null;
		
			logger.info("Entered in create order");
			logger.info("userWalletDTO =" + orderDTO);
			result = orderService.createSellOrder(orderDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

	}
	/**
	 * Get All Sell Order
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/v1/admin/order/sell")
		
		public List<SellOrder> viewAllSellOrder() {
			
			return orderService.retrieveAllSellOrder();
		}
     
	/**
	 * Get All Buy Order
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/v1/admin/order/buy")
		
		public List<BuyOrder> viewAllBuyOrder() {
			
			return orderService.retrieveAllBuyOrder();
		}	
}
