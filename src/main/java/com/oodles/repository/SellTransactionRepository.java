package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.SellTransaction;

public interface SellTransactionRepository extends JpaRepository<SellTransaction, Long>{

}
