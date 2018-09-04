package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.CryptoCurrency;
import com.oodles.repository.CurrencyRepository;

@Service
public class CryptoCurrencyService {
	@Autowired
	CurrencyRepository currencyRepository;

	// Creating Currency
	public Map<Object, Object> createCurrency(CryptoCurrency currency) {
		Map<Object, Object> result = new HashMap<>();
		CryptoCurrency newCurrency = new CryptoCurrency();
		newCurrency.setCoinName(currency.getCoinName());
		newCurrency.setInitialSupply(currency.getInitialSupply());
		newCurrency.setFees(currency.getFees());
		newCurrency.setPrice(currency.getPrice());
		newCurrency.setSymbol(currency.getSymbol());
		currencyRepository.save(newCurrency);
		result.put("responseMessage", "success");
		return result;
	}

	// Getting all currency
	public List<CryptoCurrency> retrieveAllCurrency() {
		List<CryptoCurrency> output = currencyRepository.findAll();
		return output;
	}

	// Deleting currency
	public CryptoCurrency deleteCurrency(String id) {
		Optional<CryptoCurrency> value = currencyRepository.findById(Long.parseLong(id));
		CryptoCurrency result = value.get();
		if (value.isPresent() && (!result.getCurrencyId().equals(id))) {
			currencyRepository.deleteById(Long.parseLong(id));
		}
		return result;
	}

	// Updating currency
	public CryptoCurrency updateCurrency(String currencyId, String coinName, String symbol, String fees,
			String initialSupply, String price) {
		Optional<CryptoCurrency> value = currencyRepository.findById(Long.parseLong(currencyId));
		CryptoCurrency currency = value.get();
		if (value.isPresent() && (!currency.getCoinName().equalsIgnoreCase(coinName))
				&& (!currency.getSymbol().equalsIgnoreCase(symbol)) && (!currency.getFees().equalsIgnoreCase(fees))
				&& (!currency.getInitialSupply().equalsIgnoreCase(initialSupply))
				&& (!currency.getPrice().equalsIgnoreCase(price))) {
			CryptoCurrency newCurrencys = new CryptoCurrency();
			newCurrencys.setCurrencyId(Long.parseLong(currencyId));
			newCurrencys.setCoinName(coinName);
			newCurrencys.setFees(fees);
			newCurrencys.setInitialSupply(initialSupply);
			newCurrencys.setPrice(price);
			newCurrencys.setSymbol(symbol);
			currencyRepository.save(newCurrencys);
		}
		return currency;
	}
}
