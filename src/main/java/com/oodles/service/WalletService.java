package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.CryptoWallet;
import com.oodles.domain.FiatDeposit;
import com.oodles.domain.FiatWallet;
import com.oodles.domain.User;
import com.oodles.dto.CryptoWalletDto;
import com.oodles.dto.FiatWalletDto;
import com.oodles.enumeration.CryptoName;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatCurrencyRepository;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.UserRepository;

@Service
public class WalletService {
	Logger logger = LoggerFactory.getLogger(WalletService.class);
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	private FiatWalletRepository fiatWalletRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FiatDepositRepository fiatDepositRepository;

	// Creating crypto Wallet
	public Map createCryptoWallet(CryptoWalletDto cryptoWallet) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("In service crypto wallet");
		CryptoName coinName = cryptoWallet.getCoinName();
		Long userId = cryptoWallet.getUserId();
		System.out.println(coinName + "c" + userId);

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			logger.info("in user if");
			User foundUser = user.get();
			CryptoWallet newWalletType = cryptoWalletRepository.findByCoinNameAndUser(coinName.toString(), foundUser);
			System.out.println(newWalletType);
			if (newWalletType == null) {
				logger.info("logger in newWalletType");
				CryptoWallet wallet = new CryptoWallet();
				wallet.setBalance(0.0);
				wallet.setCoinName(coinName.toString());
				wallet.setShadowBalance(0.0);
				wallet.setUser(foundUser);
				wallet.setWalletType("CRYPTO");
				cryptoWalletRepository.save(wallet);
				logger.info("seding result by service");
				result.put("responseMessage", "Crypto wallet is created");
				return result;
			}
			result.put("responseMessage", " crypto wallet alredy present");
			return result;
		}
		result.put("responseMessage", "User Not Found");
		return result;
	}

	// Creating fiat Wallet
	public Map createFiatWallet(FiatWalletDto fiatWallet) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("In service fiat wallet");
		Long userId = fiatWallet.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User foundUser = user.get();
			FiatWallet newWalletType = fiatWalletRepository.findByUser(foundUser);
			if (newWalletType == null) {
				FiatWallet wallet = new FiatWallet();
				wallet.setCoinName("RUPEES");
				wallet.setWalletType("FIAT");
				wallet.setShadowBalance(0.0);
				wallet.setBalance(0.0);
				wallet.setUser(foundUser);
				fiatWalletRepository.save(wallet);
				result.put("responseMessage", "Fiat wallet is created");
				return result;
			}
			result.put("responseMessage", "User Is fiat wallet is already there");
			return result;
		}
		result.put("responseMessage", "User Not Found");
		return result;
	}

	// Fiat Wallet History

	public List<FiatDeposit> fiatWalletHistory(Long userId) {
		List<FiatDeposit> depositsList = fiatDepositRepository.findAllByUserId(userId);
		return depositsList;
	}

}