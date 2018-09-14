package com.oodles.repository;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.SellOrder;
import com.oodles.enumeration.OrderStatus;

public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {

	List<SellOrder> findAllBySellOrderStatus(OrderStatus orderStatus);
}
