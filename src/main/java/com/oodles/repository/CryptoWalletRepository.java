package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.CryptoWallet;

public interface CryptoWalletRepository extends JpaRepository<CryptoWallet, Long>{

}
