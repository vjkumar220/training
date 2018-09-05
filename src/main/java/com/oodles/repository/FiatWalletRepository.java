package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.FiatWallet;
import com.oodles.domain.User;

public interface FiatWalletRepository extends JpaRepository<FiatWallet, Long>{
	FiatWallet findByWalletTypeAndUser(String wallet, User id);
}
