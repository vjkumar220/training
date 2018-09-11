package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.user.User;
import com.oodles.domain.wallet.FiatWallet;

public interface FiatWalletRepository extends JpaRepository<FiatWallet, Long>{
	FiatWallet findByWalletTypeAndUser(String wallet, User id);
	FiatWallet findByUser(Long id);
	FiatWallet findByUserId(Long id);
}
