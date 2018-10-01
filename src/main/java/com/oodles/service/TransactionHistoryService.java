package com.oodles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyTransaction;
import com.oodles.domain.SellTransaction;
import com.oodles.repository.BuyTransactionRepository;
import com.oodles.repository.SellTransactionRepository;

@Service
public class TransactionHistoryService {
	@Autowired
	private SellTransactionRepository sellTransactionRepository;
	@Autowired
	private BuyTransactionRepository buyTransactionRepository;
	
	public List<SellTransaction> sellTransactionHistory(Long sellerId){
		return sellTransactionRepository.findBySellerId(sellerId);
	}
	public List<BuyTransaction> buyTransactionHistory(Long buyerId){
		return buyTransactionRepository.findByBuyerId(buyerId);
	}
	
}
