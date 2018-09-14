package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.CryptoWallet;
import com.oodles.domain.User;
import com.oodles.enumeration.CryptoName;

public interface CryptoWalletRepository extends JpaRepository<CryptoWallet, Long>{
	CryptoWallet findByWalletTypeAndUser(String wallet, User id);
	CryptoWallet  findByCryptoWalletIdAndUserId(Long walletId , Long userId);
	CryptoWallet findByCoinNameAndUser(String cryptoName , User userId);
	CryptoWallet findByCoinNameAndUserId(String cryptoName , Long userId);

}
