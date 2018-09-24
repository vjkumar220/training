package com.oodles.Tasks;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.oodles.services.OrderMatchingService;

@Service
@Transactional
public class OrderMatchingTask {
	@Autowired
	private OrderMatchingService orderMatchingService;

	@Scheduled(cron = "${ordermatching.cron.expression}")
	public void sayHello() {

		orderMatchingService.orderMatch();

		System.out.println("Transaction of Buy and Sell is processing");

	}
}
