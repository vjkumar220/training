package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.SellOrder;
import com.oodles.enumeration.OrderStatus;
@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {

	List<SellOrder> findAllBySellOrderStatus(OrderStatus orderStatus);

	SellOrder findBySellOrderId(Long sellId);
	List<SellOrder> findAllByUserId(Long userId);
}
