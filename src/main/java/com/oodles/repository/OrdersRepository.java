package com.oodles.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.order.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
