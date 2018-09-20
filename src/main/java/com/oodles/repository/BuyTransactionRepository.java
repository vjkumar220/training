package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.BuyTransaction;

@Repository
public interface BuyTransactionRepository extends JpaRepository<BuyTransaction,Long> {

}