package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.CryptoDeposit;

	@Repository
	public interface CryptoDepositRepository extends JpaRepository<CryptoDeposit,Long> {
		
}
