package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
