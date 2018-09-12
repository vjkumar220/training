package com.oodles.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.currency.CryptoCurrency;
import com.oodles.domain.currency.FiatCurrency;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.CryptoCurrencyService;
import com.oodles.service.FiatCurrencyService;
import com.oodles.util.ResponseHandler;

@RestController
public class CurrencyController {

	@Autowired
	FiatCurrencyService fiatCurrencyService;

	@Autowired
	CryptoCurrencyService cryptoCurrencyService;

	private Map result = null;

	// creating crypto currency
	@PostMapping(value = "/createCryptoCurrency")
	public Map createCryptoCurrency(@RequestBody CryptoCurrency cryptoCurrency) {
		try {

			result = cryptoCurrencyService.createCurrency(cryptoCurrency);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	// creating fiat currency
	@PostMapping(value = "/createFiatCurrency")
	public Map createFiatCurrency(@RequestBody FiatCurrency fiatCurrency) {
		try {
			result = fiatCurrencyService.createFiatCurrency(fiatCurrency);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}
}
