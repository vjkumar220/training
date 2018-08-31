package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.FiatWallet;

public interface FiatWalletRepository extends JpaRepository<FiatWallet, Long>{

}
