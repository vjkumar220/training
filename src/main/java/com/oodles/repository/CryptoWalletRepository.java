package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.CryptoWallet;
import com.oodles.domain.User;

public interface CryptoWalletRepository extends JpaRepository<CryptoWallet, Long>{
	CryptoWallet findByWalletTypeAndUser(String wallet, User id);

}
