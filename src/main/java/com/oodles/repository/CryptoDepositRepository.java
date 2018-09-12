package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.deposit.CryptoDeposit;

public interface CryptoDepositRepository extends JpaRepository<CryptoDeposit, Long>{
	
	CryptoDeposit findByCryptoWalletCryptoWalletIdAndCryptoDepositId(Long walletId , Long depositId);

}
