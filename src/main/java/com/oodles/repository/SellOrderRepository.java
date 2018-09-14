package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.SellOrder;
@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder,Long> {
	List<SellOrder> findbyStatus(String status);
}
