package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.FiatDeposit;
import com.oodles.models.FiatWallet;
import com.oodles.models.User;
	@Repository
	public interface FiatDepositRepository extends JpaRepository<FiatDeposit,Long> {
		FiatDeposit findByWalletTypeAndUser(String walletType,User userid);
		FiatDeposit findByTransactionIdAndUserId(Long transactionId,Long userid);
	}


