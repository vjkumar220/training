package com.oodles.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.CryptoCurrencyDto;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.models.CryptoCurrency;
import com.oodles.services.CurrencyService;

@RestController
public class CurrencyController {
@Autowired
private CurrencyService  currencyService;

/**
 * Add Currency
 * @param cryptoCurrency
 * @return
 */

@RequestMapping(method = RequestMethod.POST, value = "/v1/currencies")

public Map addCurrency(@Valid @RequestBody CryptoCurrencyDto cryptoCurrency)  {
	Map result=null;
	
		 result=currencyService.addCurrency(cryptoCurrency);
		 return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
}
/**
 * View Currency
 * @return
 */
@GetMapping(value = "/v1/currencies")
public List<CryptoCurrency> viewAllCurrency() {
	List<CryptoCurrency> result = currencyService.retrieveAllCurrency();
	return result;
}
/**
 * Update Currency
 * @param currencyId
 * @param coinName
 * @param fees
 * @param symbol
 * @param initialSupply
 * @param price
 * @return
 */
@RequestMapping(method = RequestMethod.PUT, value = "/v1/currencies/{currencyId}/{coinName}/{fees}/{symbol}/{initialSupply}/{price}")
@ResponseBody
public Map updateCurrency(@PathVariable Long currencyId,@PathVariable String coinName,@PathVariable Long fees,@PathVariable String symbol,@PathVariable Long initialSupply,@PathVariable Long price)
{
	CryptoCurrency cryptocurrency=null;

	cryptocurrency =currencyService.updateCurrency(currencyId,coinName,fees,symbol,initialSupply,price);
	return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, cryptocurrency);

}
/**
 * Delete Currency
 * @param currencyId
 * @return
 */
@RequestMapping(method = RequestMethod.DELETE, value = "v1/currencies/{currencyId}")
@ResponseBody
public Map deleteCurrency(@PathVariable String currencyId)
{
	CryptoCurrency result=null;
		
		
		 result=currencyService.deleteCurrency(currencyId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		
}
}
