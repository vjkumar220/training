package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.order.SellOrder;

public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {

}
