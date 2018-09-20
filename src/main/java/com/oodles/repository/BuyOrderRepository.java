package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.BuyOrder;
import com.oodles.enumeration.OrderStatus;
@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long>{
	
	List<BuyOrder> findAllByBuyOrderStatus(OrderStatus status);

}
