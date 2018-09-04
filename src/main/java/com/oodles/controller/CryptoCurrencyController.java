package com.oodles.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.oodles.domain.CryptoCurrency;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.CryptoCurrencyService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping("/v1")
public class CryptoCurrencyController {
	Logger logger = LoggerFactory.getLogger(CryptoCurrencyController.class);
	
	@Autowired
	CryptoCurrencyService currencyService;
	
	
	Map<?, ?> result;
		//creating currency
	@PostMapping(value = "/currencies")
	public Map<?, ?> createCurrency(@Valid @RequestBody CryptoCurrency currency) {
		try {
			logger.info("CurrencyController - create currency in try");
			result = currencyService.createCurrency(currency);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			logger.info("UserCurrency - create currency in catch'");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	// Getting all the currency
	@GetMapping(value = "/currencies")
	public List<CryptoCurrency> getAlllCurrency() {
		logger.info("CurrencyController - get all currency");
		List<CryptoCurrency> output = currencyService.retrieveAllCurrency();
		logger.info("CurrencyController - get all currency - result");
		return output;
	}

	// deleting currency by id
	@DeleteMapping(value = "/currencies/{currencyId}")
	public @ResponseBody Map<?, ?> deleteCurrency(@PathVariable String currencyId) {
		CryptoCurrency delete = null;
		try {
			delete = currencyService.deleteCurrency(currencyId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, delete);

		} catch (ResourceNotFoundException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, delete);

		} catch (NoSuchElementException ex) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, delete);

		}

	}

	// update currency by id
	@PutMapping(value = "/currencies/{currencyId}/{coinName}/{symbol}/{fees}/{initialSupply}/{price}")
	public Map<?, ?> updateCurrency(@PathVariable String currencyId, @PathVariable String coinName,
			@PathVariable String symbol, @PathVariable String fees, @PathVariable String initialSupply,
			@PathVariable String price) {
		CryptoCurrency currency = null;
		try {
			currency = currencyService.updateCurrency(currencyId, coinName, symbol, fees, initialSupply, price);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, currency);
		} catch (ResourceNotFoundException exception) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, currency);
		} catch (NoSuchElementException excep) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, currency);
		}
	}

}
