package com.oodles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.CryptoCurrency;
import com.oodles.repository.CryptoCurrencyRepository;

@Service
public class CurrencyService {
	@Autowired
	private CryptoCurrencyRepository cryptoCurrencyRepository;
	//Add Currency
	public Map<String, Object>  addCurrency(CryptoCurrency cryptoCurrency ) {
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName=cryptoCurrency.getCoinName();
		String symbol=cryptoCurrency.getSymbol();
		Long fees=cryptoCurrency.getFees();
		Long initialSupply=cryptoCurrency.getInitialSupply();
		Long price=cryptoCurrency.getPrice();
		CryptoCurrency cryptoname = cryptoCurrencyRepository.findBycoinName(cryptoCurrency.getCoinName());
		CryptoCurrency cryptosymbol=cryptoCurrencyRepository.findBySymbol(cryptoCurrency.getSymbol());
		if(cryptoname == null && cryptosymbol==null){
			CryptoCurrency currency=new CryptoCurrency();
			currency.setCoinName(coinName);
			currency.setSymbol(symbol);
			currency.setFees(fees);
			currency.setInitialSupply(initialSupply);
			currency.setPrice(price);
			cryptoCurrencyRepository.save(currency);
			result.put("responseMessage", "success");
		}
		return result;
	}
//View All Currency
	public List<CryptoCurrency> retrieveAllCurrency(){
		List<CryptoCurrency> result = cryptoCurrencyRepository.findAll();
		return result;
	}
	//Update Currency
	
	public CryptoCurrency updateCurrency(Long currencyId,String coinName,Long fees,String symbol,Long initialSupply,Long price)
	{
		Optional<CryptoCurrency> value=cryptoCurrencyRepository.findById(currencyId);
		CryptoCurrency cryptocurrency = value.get();
		 if(value.isPresent())
		 
		{
			 CryptoCurrency currency=new CryptoCurrency();
			 currency.setCurrencyId(currencyId);
			 currency.setCoinName(coinName);
			 currency.setFees(fees);
			 currency.setInitialSupply(initialSupply);
			 currency.setPrice(price);
			 currency.setSymbol(symbol);
			 cryptoCurrencyRepository.save(currency);
		}		
		return cryptocurrency;
	}
	//Delete Currency
	
public  CryptoCurrency deleteCurrency(String currencyId) {
		
		Optional<CryptoCurrency> cryptocurrency = cryptoCurrencyRepository.findById(Long.parseLong(currencyId));
		CryptoCurrency result = cryptocurrency.get();
		if(cryptocurrency.isPresent()&& (!result.getCurrencyId().equals(currencyId)))
		{
			CryptoCurrency currency=new CryptoCurrency();
			
				
			cryptoCurrencyRepository.deleteById(Long.parseLong(currencyId));
			
			
		}
		return result;
}
	
	
	
	
}
