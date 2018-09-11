package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.currency.FiatCurrency;

@Repository
public interface FiatCurrencyRepository extends JpaRepository<FiatCurrency, Long> {
	
	FiatCurrency findByCoinName(String coinName);
	FiatCurrency findBySymbol(String symbol);
}
