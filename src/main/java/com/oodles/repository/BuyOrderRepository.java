package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.enums.OrderStatus;
import com.oodles.models.BuyOrder;
@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder,Long>{
	List<BuyOrder> findAllByStatus(OrderStatus status);
}
