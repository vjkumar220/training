package com.oodles.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.StringConstant;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.models.BuyOrder;
import com.oodles.models.BuyTransaction;
import com.oodles.models.SellOrder;
import com.oodles.models.SellTransaction;
import com.oodles.services.OrderMatchingService;
import com.oodles.services.TransactionService;

@RestController
public class OrderMatchingController {

	@Autowired
	private OrderMatchingService orderMatchingService;

	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(method = RequestMethod.GET, value = "v1/admin/orders/sell")
	public List<SellOrder> sellList(){
		return orderMatchingService.sellList();
	}


	@RequestMapping(method = RequestMethod.GET, value = "v1/admin/orders/buy")
	public List<BuyOrder> buyList(){
		return orderMatchingService.buyList();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/v1/admin/executeTransaction")

	public Map orderMatch() {
		Map result = null;
		
			result = orderMatchingService.orderMatch();
			return ResponseHandler.generateResponse(HttpStatus.OK, false, StringConstant.Success, null, result);
		
	}


	@GetMapping(value = "/v1/user/transaction/history/buyer/{buyerId}")
	public Map<String, Object> buyerHistory(@PathVariable Long buyerId) {
		List<BuyTransaction> output = transactionService.buyTransactionHistory(buyerId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, StringConstant.Success, null, output);
	}
	
	@GetMapping(value = "/v1/user/transaction/history/seller/{sellerId}")
	public Map<String, Object> sellerHistory(@PathVariable Long sellerId) {
		List<SellTransaction> output = transactionService.sellTransactionHistory(sellerId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, StringConstant.Success, null, output);
	}	



}




	

