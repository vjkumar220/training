package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.BuyTransaction;
@Repository
public interface BuyTransactionRepository extends JpaRepository<BuyTransaction, Long>{
		
	List<BuyTransaction> findByBuyerId(Long buyerId);
}
