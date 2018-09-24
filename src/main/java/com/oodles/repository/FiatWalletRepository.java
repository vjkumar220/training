package com.oodles.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.FiatWallet;
import com.oodles.models.User;

@Repository
public interface FiatWalletRepository extends JpaRepository<FiatWallet,Long> {
	FiatWallet findByWalletTypeAndUser(String walletType,User userid);
	FiatWallet findByUserId(Long userid);
	FiatWallet findByWalletId(Long walletID);
	FiatWallet findByUser(User userid);
}
