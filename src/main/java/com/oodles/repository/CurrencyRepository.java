package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.CryptoCurrency;
import com.oodles.enumeration.CryptoName;

@Repository
public interface CurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
	
	CryptoCurrency findByCoinName(CryptoName cryptoName);

}
