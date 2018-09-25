package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.service.OrderMatchingService;
import com.oodles.util.ResponseHandler;

@RestController
public class OrderMatchingController {

	@Autowired
	private OrderMatchingService orderMatchingService;

/*	@GetMapping("/sell")
	public List<SellOrder> sellList() {
		return orderMatchingService.sellList();
	}

	@GetMapping("/buy")
	public List<BuyOrder> buyList() {
		return orderMatchingService.buyList();
	}*/

	@GetMapping("v1/admin/orderMatching")
	public Map<String, Object> orderMatching() {
		String output = orderMatchingService.orderMatch();
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, output);
	}

}
