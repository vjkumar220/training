package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.FiatWallet;
import com.oodles.domain.User;
@Repository
public interface FiatWalletRepository extends JpaRepository<FiatWallet, Long>{
	FiatWallet findByUser(User id);
	FiatWallet findByUser(Long id);
	FiatWallet findByUserId(Long id);
	FiatWallet findByFiatWalletId(Long buyerFiatWalletid);
}
