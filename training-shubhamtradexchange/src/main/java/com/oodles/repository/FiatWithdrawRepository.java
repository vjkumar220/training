package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.FiatWithdraw;
@Repository
public interface FiatWithdrawRepository extends JpaRepository<FiatWithdraw,Long> {
	
	FiatWithdraw findByTransactionId(Long transactionId);
	//FiatWithdraw findByTransactionIdAndFiatwalletWalletId(Long transactionId,Long walletId);


}
