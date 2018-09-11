package com.oodles.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.currency.FiatCurrency;
import com.oodles.repository.FiatCurrencyRepository;

@Service
public class FiatCurrencyService {

	@Autowired
	FiatCurrencyRepository fiatCurrencyRepository;

	// Add new Fiat Currency
	public Map<?, ?> createFiatCurrency(FiatCurrency fiatCurrency) {
		Map result = null;
		String coinName = fiatCurrency.getCoinName();
		String symbol = fiatCurrency.getSymbol();
		FiatCurrency newFiatCurrecyCoinName = fiatCurrencyRepository.findByCoinName(coinName);
		FiatCurrency newFiatCurrecySymbol = fiatCurrencyRepository.findBySymbol(symbol);
		if (newFiatCurrecyCoinName == null && newFiatCurrecySymbol == null) {
			FiatCurrency fiatCurrency2 = new FiatCurrency();
			fiatCurrency2.setCoinName(coinName);
			fiatCurrency2.setSymbol(symbol);
			result.put("responseMessage", "success");
			return result;
		}
		result.put("responseMessage", "error");
		return result;
	}
}
