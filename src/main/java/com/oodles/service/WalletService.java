package com.oodles.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oodles.domain.CryptoWallet;
import com.oodles.domain.User;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatCurrencyRepository;
import com.oodles.repository.UserRepository;

@Service
public class WalletService {
	Logger logger = LoggerFactory.getLogger(WalletService.class);
	@Autowired
	CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	FiatCurrencyRepository fiatCurrencyRepository;
	@Autowired
	UserRepository userRepository;

	// Creating crypto Wallet
	public String createCryptoWallet(CryptoWallet cryptoWallet) {
		logger.info("In service crypto wallet");
		String coinName = cryptoWallet.getCoinName();
		String walletType = cryptoWallet.getWalletType();
		User user = cryptoWallet.getUser();
		Optional<User> userId = userRepository.findById(user.getId());
		if (userId.isPresent()) {
			CryptoWallet newCoinName = cryptoWalletRepository.findByCoinName(coinName);
			CryptoWallet newWalletType = cryptoWalletRepository.findByWalletType(walletType);
			CryptoWallet newUser= cryptoWalletRepository.findByUser(user);
			if (newCoinName == null && newWalletType == null ) {
				CryptoWallet wallet = new CryptoWallet();
				wallet.setCoinName(coinName);
				wallet.setWalletType(walletType);
				wallet.setUser(userId.get());
				cryptoWalletRepository.save(wallet);
				return "YOUR WALLET IS ADDED...";
			}
		}
		return "Your wallet is not added";

	}
}
