package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.CryptoCurrency;
import com.oodles.dto.CryptoCurrencyDto;
import com.oodles.dto.FiatCurrencyDto;
import com.oodles.service.CryptoCurrencyService;
import com.oodles.service.FiatCurrencyService;
import com.oodles.util.ResponseHandler;


@RestController
@RequestMapping(value = "")
public class CurrencyController {

	@Autowired
	FiatCurrencyService fiatCurrencyService;

	@Autowired
	CryptoCurrencyService cryptoCurrencyService;

	/**
	 * creating crypto currency
	 * 
	 * @param cryptoCurrency
	 * @return
	 */
	@PostMapping(value = "v1/admin/currency/crypto")
	public Map<String, Object> createCryptoCurrency(@Valid @RequestBody CryptoCurrencyDto cryptoCurrency) {
		Map<Object, Object> result = cryptoCurrencyService.createCurrency(cryptoCurrency);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, SUCCESS, null, result);
	}

	/**
	 * creating fiat currency
	 * 
	 * @param fiatCurrency
	 * @return
	 */
	@PostMapping(value = "v1/admin/currency/fiat/currency")
	public Map<String, Object> createFiatCurrency(@Valid @RequestBody FiatCurrencyDto fiatCurrency) {
		Map<Object, Object> result = fiatCurrencyService.createFiatCurrency(fiatCurrency);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, SUCCESS, null, result);
	}

	// Getting all existing currency

	@GetMapping("v1/admin/crypto/currencies")
	public Map<String, Object> getAllCryptoCurrency() {
		List<CryptoCurrency> currencyList = cryptoCurrencyService.getAllCurrency();
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, currencyList);

	}

	/**
	 * Deleting Crypto Currency
	 * 
	 * @param currencyId
	 * @return
	 */

	@DeleteMapping("v1/admin/crypto/currency/{currencyId}")
	public Map<String, Object> deleteCryptoCurrency(@PathVariable String currencyId) {
		String result = cryptoCurrencyService.deleteCurrency(currencyId);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);

	}

	@PutMapping(value = "v1/admin/currency/crypto/{currencyID}/fees/{fees}/initialSupply/{initialSupply}/price/{price}")
	public Map<String, Object> updateCryptoCurrency(@RequestParam Long currencyId, @RequestParam Double fees,
			@RequestParam Double initialSupply, @RequestParam Double price) {
		 String result = cryptoCurrencyService.updateCryptoCurrency(currencyId, fees, initialSupply, price);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

}
