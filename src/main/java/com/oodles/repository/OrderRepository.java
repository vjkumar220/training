package com.oodles.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.oodles.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> { 

}
