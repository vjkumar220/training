package com.oodles.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.LimitOrder;

@Repository
public interface LimitOrderRepository extends JpaRepository<LimitOrder,Long> { 

}
