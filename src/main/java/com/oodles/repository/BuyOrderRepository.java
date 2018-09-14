package com.oodles.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.BuyOrder;

public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long>{
	
	Set<BuyOrder> findAllByBuyOrderStatus(String status);

}
