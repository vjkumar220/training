package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.SellTransaction;

@Repository
public interface SellTransactionRepository extends JpaRepository<SellTransaction,Long> {



}
