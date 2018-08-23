package com.student.respsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.domain.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
}
