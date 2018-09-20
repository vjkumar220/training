package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.SellTransaction;

public interface SellTransactionRepository extends JpaRepository<SellTransaction, Long>{
	
	List<SellTransaction> findBySellerId(Long sellerId);
}
