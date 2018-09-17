package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
