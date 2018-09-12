package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.BuyOrder;
@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder,Long>{

}
