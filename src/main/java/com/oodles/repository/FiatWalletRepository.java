package com.oodles.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.FiatWallet;

@Repository
public interface FiatWalletRepository extends JpaRepository<FiatWallet,Long> {
	FiatWallet findBywalletId(Long walletId);
}
