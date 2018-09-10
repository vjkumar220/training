package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.MarketOrder;

@Repository
public interface MarketOrderRepository extends JpaRepository<MarketOrder,Long> {

}
