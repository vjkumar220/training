package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.BuyOrder;
import com.oodles.enumeration.OrderStatus;

public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long>{
	
	List<BuyOrder> findAllByBuyOrderStatus(OrderStatus status);

}
