package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.BuyTransaction;

public interface BuyTransactionRepository extends JpaRepository<BuyTransaction, Long>{

}
