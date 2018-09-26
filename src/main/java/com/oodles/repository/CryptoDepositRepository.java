package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.CryptoDeposit;
@Repository
public interface CryptoDepositRepository extends JpaRepository<CryptoDeposit, Long>{
	
	CryptoDeposit findByCryptoWalletCryptoWalletIdAndCryptoDepositId(Long walletId , Long depositId);
	
	List<CryptoDeposit> findAllByCryptoWalletCryptoWalletId(Long cryptoWalletId);

}
