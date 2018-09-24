package com.oodles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.BuyTransaction;
import com.oodles.models.SellTransaction;
import com.oodles.repository.BuyTransactionRepository;
import com.oodles.repository.SellTransactionRepository;

@Service
public class TransactionService {

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
