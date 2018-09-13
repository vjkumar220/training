package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.currency.CryptoCurrency;
import com.oodles.enumeration.CryptoName;
import com.oodles.repository.CurrencyRepository;

@Service
public class CryptoCurrencyService {
	@Autowired
	CurrencyRepository currencyRepository;

	// Creating Currency
	public Map<Object, Object> createCurrency(CryptoCurrency currency) {
		Map<Object, Object> result = new HashMap<>();
		CryptoName cryptoName = currency.getCoinName();
		CryptoCurrency cryptoCurrency = currencyRepository.findByCoinName(cryptoName);
		if (cryptoCurrency == null) {
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
		result.put("errorMessage", "Currency alredy exists");
		return result;
	}

	// Getting all currency
	public List<CryptoCurrency> getAllCurrency() {
		List<CryptoCurrency> output = currencyRepository.findAll();
		return output;
	}

	// Deleting currency
	public String deleteCurrency(String id) {
		Optional<CryptoCurrency> value = currencyRepository.findById(Long.parseLong(id));
		if (value.isPresent()) {
			CryptoCurrency result = value.get();
			if (value.isPresent() && (!result.getCurrencyId().equals(id))) {
				currencyRepository.deleteById(Long.parseLong(id));

				return "Your currency deleted";
			}
		}
		return "Crypto Currency not present";
	}

	// Updating currency
	public String updateCurrency(String currencyId, CryptoName coinName, String symbol, String fees,
			String initialSupply, String price) {
		Optional<CryptoCurrency> value = currencyRepository.findById(Long.parseLong(currencyId));
		if (value.isPresent()) {
			CryptoCurrency currency = value.get();
			if (value.isPresent() && (!currency.getCoinName().equals(coinName))
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
				return "Your currency is updated";
			}
		}
		return "Currency is not present";
	}
}
