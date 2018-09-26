package com.oodles.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.FiatCurrency;
import com.oodles.dto.FiatCurrencyDto;
import com.oodles.repository.FiatCurrencyRepository;

@Service
public class FiatCurrencyService {
	@Autowired
	private FiatCurrencyRepository fiatCurrencyRepository;
	/**
	 *  Add new Fiat Currency
	 * @param fiatCurrency
	 * @return
	 */
	public Map<Object, Object> createFiatCurrency(FiatCurrencyDto fiatCurrency) {
		Map<Object, Object> result = new HashMap<>();
		String coinName = fiatCurrency.getCoinName();
		String symbol = fiatCurrency.getSymbol();
		FiatCurrency newFiatCurrecyCoinName = fiatCurrencyRepository.findByCoinName(coinName);
		FiatCurrency newFiatCurrecySymbol = fiatCurrencyRepository.findBySymbol(symbol);
		if (newFiatCurrecyCoinName == null && newFiatCurrecySymbol == null) {
			FiatCurrency fiatCurrency2 = new FiatCurrency();
			fiatCurrency2.setCoinName(coinName);
			fiatCurrency2.setSymbol(symbol);
			fiatCurrencyRepository.save(fiatCurrency2);
			result.put("responseMessage", "Fiat Currency is generated");
			return result;
		}
		result.put("responseMessage", "error");
		return result;
	}
}
