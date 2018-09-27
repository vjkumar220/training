package com.oodles.Tasks;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.oodles.enums.OrderStatus;
import com.oodles.models.BuyOrder;
import com.oodles.models.CryptoWallet;
import com.oodles.models.SellOrder;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.services.AdminSupplyService;
import com.oodles.services.OrderMatchingService;

@Service
@Transactional
public class OrderMatchingTask {
	@Autowired
	private OrderMatchingService orderMatchingService;
	@Autowired
	private AdminSupplyService adminSupplyService;
	@Autowired
	private BuyOrderRepository buyOrderRepository;
	@Autowired
	private SellOrderRepository sellOrderRepository;
	@Scheduled(cron = "${ordermatching.cron.expression}")
	public void sayHello() {
		List<BuyOrder> buyOrderList = buyOrderRepository.findAllByStatus(OrderStatus.PENDING);
		List<SellOrder> sellOrderList = sellOrderRepository.findAllByStatus(OrderStatus.PENDING);
		if (buyOrderList != null ) {

		
		orderMatchingService.orderMatch();
		adminSupplyService.SupplyUpdation();
		System.out.println("Transaction of Buy and Sell is processing");
		
		}
}}
