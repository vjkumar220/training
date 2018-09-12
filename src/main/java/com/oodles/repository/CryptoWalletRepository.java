package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.user.User;
import com.oodles.domain.wallet.CryptoWallet;

public interface CryptoWalletRepository extends JpaRepository<CryptoWallet, Long>{
	CryptoWallet findByWalletTypeAndUser(String wallet, User id);
	CryptoWallet  findByUserId( Long id );

}
