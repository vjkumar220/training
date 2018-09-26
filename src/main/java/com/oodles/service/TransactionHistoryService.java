package com.oodles.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyTransaction;
import com.oodles.domain.CryptoDeposit;
import com.oodles.domain.FiatDeposit;
import com.oodles.domain.SellTransaction;
import com.oodles.repository.BuyTransactionRepository;
import com.oodles.repository.CryptoDepositRepository;
import com.oodles.repository.CryptoWithdrawRepository;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.FiatWithdrawRepository;
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
