package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.order.BuyOrder;

public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long>{

}
