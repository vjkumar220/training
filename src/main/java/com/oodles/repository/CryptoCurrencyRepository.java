package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.CryptoCurrency;

@Repository
public interface CryptoCurrencyRepository  extends JpaRepository<CryptoCurrency,Long> {
	CryptoCurrency findBySymbol(String symbol);
	CryptoCurrency findBycoinName(String coinName);
	
}
