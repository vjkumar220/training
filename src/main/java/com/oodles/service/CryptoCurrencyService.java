package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.CryptoCurrency;
import com.oodles.domain.CryptoWallet;
import com.oodles.domain.SellOrder;
import com.oodles.domain.User;
import com.oodles.dto.CryptoCurrencyDto;
import com.oodles.enumeration.CryptoName;
import com.oodles.enumeration.OrderStatus;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.UserRepository;

@Service
public class CryptoCurrencyService {
	Logger log = LoggerFactory.getLogger(CryptoCurrencyService.class);
	@Autowired
	private CryptoCurrencyRepository currencyRepository;
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SellOrderRepository sellOrderRepository;

	/**
	 * Creating Currency
	 * 
	 * @param currency
	 * @return
	 */
	public Map<Object, Object> createCurrency(CryptoCurrencyDto currency) {
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
			log.info("Currency created");
			Optional<User> user = userRepository.findById((long) 3);
			log.info("User is find");
			if (user.isPresent()) {
				User foundUser = user.get();
				CryptoCurrency findCryptoCurrency = currencyRepository.findByCoinName(currency.getCoinName());
				if (findCryptoCurrency != null) {
					log.info("Cureency is find");
					CryptoWallet newWalletType = cryptoWalletRepository
							.findByCoinNameAndUser(currency.getCoinName().toString(), foundUser);
					log.info("Cureency is find");
					if (newWalletType == null) {
						log.info("wall");
						CryptoWallet wallet = new CryptoWallet();
						wallet.setBalance(currency.getInitialSupply());
						wallet.setCoinName(currency.getCoinName().toString());
						wallet.setShadowBalance(0.0);
						wallet.setUser(foundUser);
						wallet.setWalletType("CRYPTO");
						cryptoWalletRepository.save(wallet);
						SellOrder sellOrder = new SellOrder();
						sellOrder.setOrderPrice(currency.getPrice() * currency.getInitialSupply());
						sellOrder.setRemainingSellCoinQuantity(currency.getInitialSupply());
						sellOrder.setSellCoinName(currency.getCoinName().toString());
						sellOrder.setSellCoinQuantity(currency.getInitialSupply());
						sellOrder.setSellOrderStatus(OrderStatus.PENDING);
						sellOrder.setSellPrice(currency.getPrice());
						sellOrder.setUser(foundUser);
						sellOrderRepository.save(sellOrder);
						result.put("responseMessage", "Crypto currency is generated");
						return result;
					}
				}
			}
		}
		result.put("responseMessage", "Currency alredy exists");
		return result;
	}

	/**
	 * Getting all currency
	 * 
	 * @return
	 */
	public List<CryptoCurrency> getAllCurrency() {
		List<CryptoCurrency> updatedListOfCryptoCurrency = currencyRepository.findAll();
		return updatedListOfCryptoCurrency;
	}

	/**
	 * Deleting currency
	 * 
	 * @param id
	 * @return
	 */
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

	/**
	 * Updating currency
	 * 
	 * @param currencyId
	 * @param coinName
	 * @param symbol
	 * @param fees
	 * @param initialSupply
	 * @param price
	 * @return
	 */
	public String updateCurrency(Long currencyId, CryptoName coinName, String symbol, Double fees, Double initialSupply,
			Double price) {
		Optional<CryptoCurrency> value = currencyRepository.findById(currencyId);
		if (value.isPresent()) {
			CryptoCurrency currency = value.get();
			if (value.isPresent() && (!currency.getCoinName().equals(coinName))
					&& (!currency.getSymbol().equalsIgnoreCase(symbol)) && (!currency.getFees().equals(fees))
					&& (!currency.getInitialSupply().equals(initialSupply)) && (!currency.getPrice().equals(price))) {
				CryptoCurrency newCurrencys = new CryptoCurrency();
				newCurrencys.setCurrencyId(currencyId);
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

	/**
	 * Updating Crypto Currency
	 * 
	 * @param currencyId
	 * @param fees
	 * @param initialSupply
	 * @param price
	 * @return
	 */

	public String updateCryptoCurrency(Long currencyId, Double fees, Double initialSupply, Double price) {
		Optional<CryptoCurrency> findCurrency = currencyRepository.findById(currencyId);
		if (findCurrency.isPresent()) {
			CryptoCurrency cryptoCurrency = findCurrency.get();
			if ((!cryptoCurrency.getFees().equals(fees)) && (!cryptoCurrency.getInitialSupply().equals(initialSupply))
					&& (!cryptoCurrency.getPrice().equals(price))) {
				cryptoCurrency.setFees(fees);
				cryptoCurrency.setInitialSupply(initialSupply);
				cryptoCurrency.setPrice(price);
				currencyRepository.save(cryptoCurrency);
				CryptoWallet cryptoWallet = cryptoWalletRepository
						.findByCoinNameAndUserId(cryptoCurrency.getCoinName().toString(), (long) 3);
				if (cryptoWallet != null) {
					cryptoWallet.setBalance(initialSupply);
					cryptoWallet.setShadowBalance(0.0);
					cryptoWalletRepository.save(cryptoWallet);
				}
				SellOrder sellOrder = sellOrderRepository.findBySellCoinNameAndUserId(cryptoCurrency.getCoinName().toString(),(long)3);
				if (sellOrder != null) {
					sellOrder.setSellPrice(price);
					sellOrder.setRemainingSellCoinQuantity(initialSupply);
					sellOrder.setSellCoinQuantity(initialSupply);
					sellOrder.setOrderPrice(price * initialSupply);
					sellOrderRepository.save(sellOrder);
				}
				return "Your crypto currency is updated";
			}
			return "Already existing value you are updating";
		}
		return "Currency Not Found";
	}
}
