package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.SellTransaction;

@Repository
public interface SellTransactionRepository extends JpaRepository<SellTransaction,Long> {

	List<SellTransaction> findBySellerId(Long sellerId);

}
