package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.BuyTransaction;
import com.oodles.domain.SellTransaction;
import com.oodles.service.TransactionHistoryService;
import com.oodles.util.ResponseHandler;

@RestController
public class TransactionHistoryController {
	
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	@GetMapping(value = "v1/user/transaction/history/buyer/{buyerId}")
	public Map<String, Object> buyerHistory(@PathVariable Long buyerId) {
		List<BuyTransaction> output = transactionHistoryService.buyTransactionHistory(buyerId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, output);
	}
	
	@GetMapping(value = "v1/user/transaction/history/seller/{sellerId}")
	public Map<String, Object> sellerHistory(@PathVariable Long sellerId) {
		List<SellTransaction> output = transactionHistoryService.sellTransactionHistory(sellerId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, output);
	}

}
