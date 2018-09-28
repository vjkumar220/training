package com.oodles.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.CryptoWallet;
import com.oodles.models.User;
@Repository
public interface CryptoWalletRepository extends JpaRepository<CryptoWallet,Long> {
	CryptoWallet findByWalletTypeAndUser(String walletType,User userid); 
	CryptoWallet findByWalletId(Long walletID);
	CryptoWallet findByCoinNameAndUserId(String coinName,Long id);
	List<CryptoWallet> findByUserId(Long userid);
}
