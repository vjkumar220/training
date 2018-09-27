package com.oodles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.dto.CryptoCurrencyDto;
import com.oodles.enums.OrderStatus;
import com.oodles.models.CryptoCurrency;
import com.oodles.models.CryptoWallet;
import com.oodles.models.FiatWallet;
import com.oodles.models.SellOrder;
import com.oodles.models.User;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.UserRepository;

@Service
public class CurrencyService {
	@Autowired
	private CryptoCurrencyRepository cryptoCurrencyRepository;
	@Autowired
	private SellOrderRepository sellOrderRepository;
	@Autowired
	private FiatWalletRepository fiatWalletRepository;

	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;

	@Autowired
	private UserRepository userRepository;
	/**
	 * Add Currency
	 * @param cryptoCurrency
	 * @return
	 */
	public Map<String, Object>  addCurrency(CryptoCurrencyDto cryptoCurrency ) {
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName=cryptoCurrency.getCoinName();
		String symbol=cryptoCurrency.getSymbol();
		Long fees=cryptoCurrency.getFees();
		Double initialSupply=cryptoCurrency.getInitialSupply();
		Double price=cryptoCurrency.getPrice();
		
		CryptoCurrency cryptoname = cryptoCurrencyRepository.findBycoinName(cryptoCurrency.getCoinName());
		CryptoCurrency cryptosymbol=cryptoCurrencyRepository.findBySymbol(cryptoCurrency.getSymbol());
		if(cryptoname == null && cryptosymbol==null){
			CryptoCurrency currency=new CryptoCurrency();
			currency.setCoinName(coinName);
			currency.setSymbol(symbol);
			currency.setFees(fees);
			currency.setInitialSupply(initialSupply);
			currency.setPrice(price);
			
			
			//Create a crypto wallet for admin
             Long userId=(long) 2;
			Optional<User> user = userRepository.findById(userId);
			if (user.isPresent()) {
				
				User foundUser = user.get();
				if(foundUser.getEnabled().equalsIgnoreCase("Active"))
				{
				CryptoWallet newWalletType = cryptoWalletRepository.findByCoinNameAndUserId(coinName.toString(), foundUser.getId());
				
				if (newWalletType == null) {
					
					CryptoWallet wallet = new CryptoWallet();
					wallet.setBalance(initialSupply);
					wallet.setCoinName(coinName.toString());
					wallet.setShadowBalance(initialSupply);
					wallet.setUser(foundUser);
					wallet.setWalletType("Crypto");
	//create fiat wallet of admin
					
						FiatWallet newWalletsType = fiatWalletRepository.findByUser(foundUser);
						if (newWalletsType == null) {
						
							FiatWallet fwallet = new FiatWallet();
							fwallet.setCoinName("INR");
							fwallet.setWalletType("Fiat");
							fwallet.setShadowBalance(0.0);
							fwallet.setBalance(0.0);
							fwallet.setUser(foundUser);
			//sell order generation
							SellOrder newOrder = new SellOrder();
							newOrder.setCoinName(coinName);
							newOrder.setSellDesiredPrice(price);
							newOrder.setCoinQuantity(initialSupply);
							newOrder.setRemainingCoin(initialSupply);
							OrderStatus status = newOrder.getStatus();
							newOrder.setStatus(status.PENDING);
							newOrder.setUser(foundUser);
							sellOrderRepository.save(newOrder);
							
							
							
							fiatWalletRepository.save(fwallet);
					
					cryptoWalletRepository.save(wallet);
					cryptoCurrencyRepository.save(currency);
				
			result.put("responseMessage", "success");
		}}}}}
		return result;
	}
	/**
	 * View All Currency
	 * @return
	 */
	public List<CryptoCurrency> retrieveAllCurrency(){
		 
		return cryptoCurrencyRepository.findAll();
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
	
	public CryptoCurrency updateCurrency(Long currencyId,String coinName,Long fees,String symbol,Double initialSupply,Double price)
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
	/**
	 * Delete Currency
	 * @param currencyId
	 * @return
	 */
	
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
