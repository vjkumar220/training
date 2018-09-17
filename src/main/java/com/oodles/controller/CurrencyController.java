package com.oodles.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.CryptoCurrency;
import com.oodles.domain.FiatCurrency;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.CryptoCurrencyService;
import com.oodles.service.FiatCurrencyService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	FiatCurrencyService fiatCurrencyService;

	@Autowired
	CryptoCurrencyService cryptoCurrencyService;

	/**
	 * creating crypto currency
	 * @param cryptoCurrency
	 * @return
	 */
	@PostMapping(value = "/create/crypto/currency")
	public Map<String, Object> createCryptoCurrency(@RequestBody CryptoCurrency cryptoCurrency) {
		Map<Object, Object> result = null;
		try {

		 result = cryptoCurrencyService.createCurrency(cryptoCurrency);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	/**
	 * creating fiat currency
	 * @param fiatCurrency
	 * @return
	 */
	@PostMapping(value = "/create/fiat/currency")
	public Map<String, Object> createFiatCurrency(@RequestBody FiatCurrency fiatCurrency) {
		Map result = null;
		try {
			 result = fiatCurrencyService.createFiatCurrency(fiatCurrency);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

	// Getting all existing currency

	@GetMapping("/all/crypto/currnecy")
	public Map<String, Object> getAllCryptoCurrency() {

		Map<String, Object> result = null;
		try {
			List<CryptoCurrency> currencyList = cryptoCurrencyService.getAllCurrency();
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, currencyList);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

	/**
	 * Deleting Crypto Currency
	 * @param currencyId
	 * @return
	 */

	@DeleteMapping("/delete/crypto/currency/{currencdId}")
	public Map<String, Object> deleteCryptoCurrency(@PathVariable String currencyId) {
		String result = null;
		try {
			 result = cryptoCurrencyService.deleteCurrency(currencyId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

}
