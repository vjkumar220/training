package com.oodles.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.user.User;
import com.oodles.domain.wallet.CryptoWallet;
import com.oodles.domain.wallet.FiatWallet;
import com.oodles.dto.CryptoWalletDto;
import com.oodles.dto.FiatWalletDto;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatCurrencyRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.UserRepository;

@Service
public class WalletService {
	Logger logger = LoggerFactory.getLogger(WalletService.class);
	@Autowired
	CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	FiatWalletRepository fiatWalletRepository;
	@Autowired
	UserRepository userRepository;

	// Creating crypto Wallet
	public Map createCryptoWallet(CryptoWalletDto cryptoWallet) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("In service crypto wallet");
		String coinName = cryptoWallet.getCoinName();
		String walletType = cryptoWallet.getWalletType();
		Long shadowBalance = cryptoWallet.getShadowBalance();
		Long balance = cryptoWallet.getBalance();
		Long userId = cryptoWallet.getUserId();
		System.out.println(coinName+""+walletType+""+shadowBalance+""+balance+""+ userId);
		
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			logger.info("in user if");
			User foundUser = user.get();
			CryptoWallet newWalletType = cryptoWalletRepository.findByWalletTypeAndUser(walletType, foundUser);
			//System.out.println(newWalletType);
			if (newWalletType != null) {
				logger.info("logger in newWalletType");
				CryptoWallet wallet = new CryptoWallet();
				wallet.setCoinName(coinName);
				wallet.setWalletType(walletType);
				wallet.setShadowBalance(shadowBalance);
				wallet.setBalance(balance);
				wallet.setUser(foundUser);
				cryptoWalletRepository.save(wallet);
				logger.info("seding result by service");
				result.put("responseMessage", "success");
			}

		}
		return result;
	}

	// Creating fiat Wallet
	public Map createFiatWallet(FiatWalletDto fiatWallet) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("In service fiat wallet");
		String coinName = fiatWallet.getCoinName();
		String walletType = fiatWallet.getWalletType();
		Long shadowBalance = fiatWallet.getShadowBalance();
		Double balance = fiatWallet.getBalance();
		Long userId = fiatWallet.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User foundUser = user.get();
			FiatWallet newWalletType = fiatWalletRepository.findByWalletTypeAndUser(walletType, foundUser);
			if (newWalletType == null) {
				FiatWallet wallet = new FiatWallet();
				wallet.setCoinName(coinName);
				wallet.setWalletType(walletType);
				wallet.setShadowBalance(shadowBalance);
				wallet.setBalance(balance);
				wallet.setUser(foundUser);
				fiatWalletRepository.save(wallet);
				result.put("responseMessage", "success");
			}

		}
		return result;
	}
}