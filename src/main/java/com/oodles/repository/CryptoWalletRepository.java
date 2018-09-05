package com.oodles.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.CryptoWallet;
import com.oodles.models.User;
@Repository
public interface CryptoWalletRepository extends JpaRepository<CryptoWallet,Long> {
	CryptoWallet findByWalletTypeAndUser(String walletType,User userid); 
}
